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
import edu.brandeis.spring.mvc.domain.Supplier;
import edu.brandeis.spring.mvc.service.InventoryItemGrid;
import edu.brandeis.spring.mvc.service.InventoryItemService;
import edu.brandeis.spring.mvc.service.Message;
import edu.brandeis.spring.mvc.service.SupplierService;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.web.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/inventory")
@Controller
public class InventoryItemController {
    private final Logger logger = LoggerFactory.getLogger(InventoryItemController.class);

    private InventoryItemService itemService;
    private SupplierService supplierService;
    private AuditLogService auditLogService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing items and suppliers");

        List<InventoryItem> items = itemService.findAll();
        uiModel.addAttribute("inventoryitems", items);

        List<Supplier> suppliers = supplierService.findAll();
        uiModel.addAttribute("suppliers", suppliers);

        logger.info("No. of inventory items: " + items.size());
        logger.info("No. of suppliers: " + suppliers.size());

        return "inventory/list";
        
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public String show(@PathVariable("itemId") Long id, Model uiModel) {
        InventoryItem item = itemService.findById(id);
        uiModel.addAttribute("item", item);

        logger.info("Inventory supplier ID: " + item.getSupplierId());
        Supplier supplier = supplierService.findById((long) item.getSupplierId());
        uiModel.addAttribute("supplier", supplier);

        return "inventory/showProduct";
    }

    @RequestMapping(value = "/{itemId}", params = "form", method = RequestMethod.POST)
    public String update(@Valid InventoryItem item, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating inventory item: " + item.getItemId());
        if (bindingResult.hasErrors() || null == item.getItemId()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("inventoryitem_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("inventoryitem", item);
            return "inventory/addInventoryItem";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("inventoryitem_save_success", new Object[]{}, locale)));
        itemService.save(item);
        
        auditLogService.saveData("Update", "Inventory Item Updated", "Admin");
        
        return "redirect:/inventory/" + UrlUtil.encodeUrlPathSegment(item.getItemId().toString(),
                httpServletRequest);
    }

    @RequestMapping(value = "delete/{itemId}",  method = RequestMethod.GET)
    public String delete(@PathVariable("itemId") Long id, Model uiModel)                         {
        logger.info("Deleting inventory item: " + id);
       
        InventoryItem item = itemService.findById(id);
        
        // Item will not be deleted from the database. Instead inventoryOnHand field will be set to zero. 
        item.setInventoryOnHand(0);
        itemService.save(item);
        
        auditLogService.saveData("Delete", "Inventory On Hand is set to 0", "Admin");
        
        item = itemService.findById(id);
        uiModel.addAttribute("item", item);

        logger.info("Inventory supplier ID: " + item.getSupplierId());
        Supplier supplier = supplierService.findById((long) item.getSupplierId());
        uiModel.addAttribute("supplier", supplier);        
                
        return "inventory/showProduct";    
        //return "redirect:/inventory/";
    }
    
    @RequestMapping(value = "/{itemId}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("itemId") Long id, Model uiModel) {
        uiModel.addAttribute("inventoryitem", itemService.findById(id));
        return "inventory/addInventoryItem";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(InventoryItem item, BindingResult bindingResult, Model uiModel, 
        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
        Locale locale) {

        logger.info("Creating Inventory Item");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "inventoryitem_save_fail");
            uiModel.addAttribute("inventoryitem", item);  
            return "inventory/addInventoryItem";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("inventoryitem_save_success", new Object[]{}, locale)));

        logger.info("Inventory Item id: " + item.getItemId());

        itemService.save(item);
        auditLogService.saveData("Create", "Inventory item is created", "Admin");
        return "redirect:/inventory/";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        InventoryItem item = new InventoryItem();
        uiModel.addAttribute("inventoryitem", item);

        return "inventory/addInventoryItem";
    }

    @ResponseBody  
    @RequestMapping(value = "/itemlistgrid", method = RequestMethod.GET, produces="application/json")
    public InventoryItemGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows,
                                      @RequestParam(value = "sidx", required = false) String sortBy,
                                      @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing items for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing items for grid with sort: {}, order: {}", sortBy, order);

        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("itemName")) {
            orderBy = "itemName";
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

        Page<InventoryItem> itemPage = itemService.findAllByPage(pageRequest);

        // Construct the grid data that will return as JSON data
        InventoryItemGrid itemGrid = new InventoryItemGrid();

        itemGrid.setCurrentPage(itemPage.getNumber() + 1);
        itemGrid.setTotalPages(itemPage.getTotalPages());
        itemGrid.setTotalRecords(itemPage.getTotalElements());

        itemGrid.setItemData(Lists.newArrayList(itemPage.iterator()));

        return itemGrid;
    }

    @Autowired
    public void setInventoryService(InventoryItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
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
