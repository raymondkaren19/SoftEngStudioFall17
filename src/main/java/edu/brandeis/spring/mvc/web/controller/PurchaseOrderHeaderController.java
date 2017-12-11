package edu.brandeis.spring.mvc.web.controller;

import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
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
import edu.brandeis.spring.mvc.service.PurchaseOrderHeaderGrid;
import edu.brandeis.spring.mvc.service.PurchaseOrderHeaderService;
import edu.brandeis.spring.mvc.service.PurchaseOrdersService;
import edu.brandeis.spring.mvc.service.SupplierService;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.web.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/header")
@Controller
public class PurchaseOrderHeaderController {
    private final Logger logger = LoggerFactory.getLogger(PurchaseOrderHeaderController.class);

    private PurchaseOrderHeaderService purchaseOrderHeaderService;
    private PurchaseOrdersService purchaseOrdersService;
    private AuditLogService auditLogService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing purchase order headers and purchase orders");

        List<PurchaseOrderHeader> header = purchaseOrderHeaderService.findAll();
        uiModel.addAttribute("header", header);

        List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findAll();
        uiModel.addAttribute("purchaseOrders", purchaseOrders);

        logger.info("No. of headers: " + header.size());
        logger.info("No. of purchase orders: " + purchaseOrders.size());

        return "header/list";
        
    }

    @RequestMapping(value = "/{headerId}", method = RequestMethod.GET)
    public String show(@PathVariable("headerId") Long id, Model uiModel) {
        PurchaseOrderHeader header = purchaseOrderHeaderService.findById(id);
        uiModel.addAttribute("header", header);

        logger.info("Purchase order ID: " + header.getId());
        PurchaseOrders order = purchaseOrdersService.findById((long) header.getId());
        uiModel.addAttribute("order", order);

        return "header/showHeader";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{headerId}", params = "form", method = RequestMethod.POST)
    public String update(@Valid PurchaseOrderHeader header, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating inventory item: " + header.getId());
        if (bindingResult.hasErrors() || null == header.getId()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("header_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("header", header);
            return "header/AddHeader";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("header_save_success", new Object[]{}, locale)));
        purchaseOrderHeaderService.save(header);
        
        auditLogService.saveData("Update", "Purchase Order Header Updated", "Admin");
        
        return "redirect:/header/" + UrlUtil.encodeUrlPathSegment(header.getId().toString(),
                httpServletRequest);
    }

    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{headerId}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("headerId") Long id, Model uiModel) {
        uiModel.addAttribute("header", purchaseOrderHeaderService.findById(id));
        return "header/addHeader";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(PurchaseOrderHeader header, BindingResult bindingResult, Model uiModel, 
        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
        Locale locale) {

        logger.info("Creating purchase order header");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "header_save_fail");
            uiModel.addAttribute("header", header);  
            return "header/addHeader";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("header_save_success", new Object[]{}, locale)));

        logger.info("Header id: " + header.getId());

        purchaseOrderHeaderService.save(header);
        auditLogService.saveData("Create", "Purchase Order Header has been created", "Admin");
        return "redirect:/header/";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        PurchaseOrderHeader header = new PurchaseOrderHeader();
        uiModel.addAttribute("header", header);

        return "header/addHeader";
    }

    @ResponseBody  
    @RequestMapping(value = "/headerlistgrid", method = RequestMethod.GET, produces="application/json")
    public PurchaseOrderHeaderGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows,
                                      @RequestParam(value = "sidx", required = false) String sortBy,
                                      @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing items for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing items for grid with sort: {}, order: {}", sortBy, order);

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

        Page<PurchaseOrderHeader> headerPage = purchaseOrderHeaderService.findAllByPage(pageRequest);

        // Construct the grid data that will return as JSON data
        PurchaseOrderHeaderGrid headerGrid = new PurchaseOrderHeaderGrid();

        headerGrid.setCurrentPage(headerPage.getNumber() + 1);
        headerGrid.setTotalPages(headerPage.getTotalPages());
        headerGrid.setTotalRecords(headerPage.getTotalElements());

        headerGrid.setPurchaseOrderHeaderData(Lists.newArrayList(headerPage.iterator()));

        return headerGrid;
    }

    @Autowired
    public void setPurchaseOrderHeaderService(PurchaseOrderHeaderService purchaseOrderHeaderService) {
        this.purchaseOrderHeaderService = purchaseOrderHeaderService;
    }

    @Autowired
    public void setPurchaseOrderService(PurchaseOrdersService purchaseOrdersService) {
        this.purchaseOrdersService = purchaseOrdersService;
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
