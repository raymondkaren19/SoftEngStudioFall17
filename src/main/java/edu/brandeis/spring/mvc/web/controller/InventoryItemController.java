package edu.brandeis.spring.mvc.web.controller;

import java.util.List;
import java.util.Locale;

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
import edu.brandeis.spring.mvc.service.InventoryItemGrid;
import edu.brandeis.spring.mvc.service.InventoryItemService;
import edu.brandeis.spring.mvc.service.Message;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/inventory")
@Controller
public class InventoryItemController {
    private final Logger logger = LoggerFactory.getLogger(InventoryItemController.class);

    private InventoryItemService itemService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing items");

        List<InventoryItem> items = itemService.findAll();
        uiModel.addAttribute("inventoryitems", items);
           
        logger.info("No. of inventory items: " + items.size());

        return "inventory/list";
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        InventoryItem item = itemService.findById(id);
        uiModel.addAttribute("InventoryItems", item);

        return "inventory/showProduct";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(InventoryItem item, BindingResult bindingResult, Model uiModel, 
        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
        Locale locale) {

        logger.info("Creating Inventory Item");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "inventoryitem_save_fail");
            uiModel.addAttribute("InventoryItem", item);  
            return "inventory/addInventoryItem";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("inventoryitem_save_success", new Object[]{}, locale)));

        logger.info("Inventory Item id: " + item.getItemId());

        itemService.save(item);
        return "redirect:/inventory/";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)    
    public String createForm(Model uiModel) {
        InventoryItem item = new InventoryItem();
        uiModel.addAttribute("inventoryitem", item);

        return "inventory/addInventoryItem";
    }

    @ResponseBody  
    @RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json")
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
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
