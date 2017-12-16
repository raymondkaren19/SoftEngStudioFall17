package edu.brandeis.spring.mvc.web.controller;

import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.domain.InventoryItem;
import edu.brandeis.spring.mvc.domain.PurchaseOrderHeader;
import edu.brandeis.spring.mvc.domain.PurchaseOrders;
import edu.brandeis.spring.mvc.domain.Supplier;
import edu.brandeis.spring.mvc.service.InventoryItemGrid;
import edu.brandeis.spring.mvc.service.InventoryItemService;
import edu.brandeis.spring.mvc.service.Message;
import edu.brandeis.spring.mvc.service.PurchaseOrderHeaderService;
import edu.brandeis.spring.mvc.service.PurchaseOrdersGrid;
import edu.brandeis.spring.mvc.service.PurchaseOrdersService;
import edu.brandeis.spring.mvc.service.SupplierService;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.web.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/orders")
@Controller
public class PurchaseOrdersController {
    private final Logger logger = LoggerFactory.getLogger(PurchaseOrdersController.class);
    
    private PurchaseOrderHeaderService purchaseOrderHeaderService;
    private PurchaseOrdersService purchaseOrdersService;
    private InventoryItemService itemService;
    private AuditLogService auditLogService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing purchase order items");

        List<PurchaseOrders> orders = purchaseOrdersService.findAll();
        uiModel.addAttribute("Orders", orders);
        logger.info("No. of items: " + orders.size());

        return "orders/list";
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public String show(@PathVariable("orderId") Long id, Model uiModel) {
        PurchaseOrders order = purchaseOrdersService.findById(id);
        uiModel.addAttribute("order", order);

        logger.info("Inventory item ID: " + order.getItemId());
        InventoryItem item = itemService.findById((long) order.getItemId());
        uiModel.addAttribute("item", item);

        return "orders/showOrders";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{orderId}", params = "form", method = RequestMethod.POST)
    public String update(@Valid PurchaseOrders order, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating purchase order: " + order.getID());

        if (bindingResult.hasErrors() || null == order.getID()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("purchaseorders_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("order", order);
            return "orders/addOrder";
        }
        uiModel.asMap().clear();

        PurchaseOrderHeader header = purchaseOrderHeaderService.findById(order.getPurchaseOrderId());
        header.setOrderTotalPrice(order.getUnitPrice() * order.getQtyOrdered());
        header.setSupplierId((long) itemService.findById(order.getItemId()).getSupplierId());
        purchaseOrderHeaderService.save(header);

        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("purchaseorders_save_success", new Object[]{}, locale)));
        purchaseOrdersService.save(order);        
        auditLogService.saveData("Update", "Purchase Order Updated", "Admin");
        
        return "redirect:/header/" + UrlUtil.encodeUrlPathSegment(header.getID().toString(),
                httpServletRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{orderId}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("orderId") Long id, Model uiModel) {
        uiModel.addAttribute("order", purchaseOrdersService.findById(id));
        return "orders/addOrder";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(PurchaseOrders order, BindingResult bindingResult, Model uiModel, 
        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
        Locale locale) {

        logger.info("Creating purchase order");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "inventoryitem_save_fail");
            uiModel.addAttribute("order", order);  
            return "orders/addOrder";
        }
        uiModel.asMap().clear();

        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("purchaseorders_save_success", new Object[]{}, locale)));

        PurchaseOrderHeader header = new PurchaseOrderHeader();
        header.setSupplierId((long) itemService.findById(order.getItemId()).getSupplierId());
        header.setOrderTotalPrice(order.getUnitPrice() * order.getQtyOrdered());
        purchaseOrderHeaderService.save(header);

        order.setPurchaseOrderId(header.getID());
        purchaseOrdersService.save(order);

        auditLogService.saveData("Create", "Purchase order is created", "Admin");

        return "redirect:/inventory/";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        PurchaseOrders order = new PurchaseOrders();
        uiModel.addAttribute("order", order);

        return "orders/addOrder";
    }

    @ResponseBody
    @RequestMapping(value = "/orderslistgrid", method = RequestMethod.GET, produces="application/json")
    public PurchaseOrdersGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "rows", required = false) Integer rows,
                                       @RequestParam(value = "sidx", required = false) String sortBy,
                                       @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing orders for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing orders for grid with sort: {}, order: {}", sortBy, order);

        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("ID")) {
            orderBy = "ID";
        }

        if (orderBy != null && order != null) {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }

        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = null;

        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }

        List<PurchaseOrders> pageContent = purchaseOrdersService.findByPurchaseOrderId();
        Page<PurchaseOrders> ordersPage = new PageImpl<PurchaseOrders>(pageContent, pageRequest, pageContent.size());

        // Construct the grid data that will return as JSON data
        PurchaseOrdersGrid ordersGrid = new PurchaseOrdersGrid();

        ordersGrid.setCurrentPage(ordersPage.getNumber() + 1);
        ordersGrid.setTotalPages(ordersPage.getTotalPages());
        ordersGrid.setTotalRecords(ordersPage.getTotalElements());

        logger.info("Elements: " + ordersPage.getTotalElements());

        ordersGrid.setPurchaseOrdersData(Lists.newArrayList(ordersPage.iterator()));

        return ordersGrid;
    }

    
    @Autowired
    public void setPurchaseOrderHeaderService(PurchaseOrderHeaderService purchaseOrderHeaderService) {
        this.purchaseOrderHeaderService = purchaseOrderHeaderService;
    }
    
    @Autowired
    public void setPurchaseOrdersService(PurchaseOrdersService purchaseOrdersService) {
        this.purchaseOrdersService = purchaseOrdersService;
    }
    
    @Autowired
    public void setInventoryService(InventoryItemService itemService) {
        this.itemService = itemService;
    }

   
    @Autowired
    public void setAuditLogService(AuditLogService auditService) {
        this.auditLogService = auditService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
}
