package edu.brandeis.spring.mvc.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import edu.brandeis.spring.mvc.domain.*;

@RequestMapping("/items")
@Controller
public class InventoryItemController {
    private final Logger logger = LoggerFactory.getLogger(InventoryItemController.class);

    private InventoryItemService itemService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing items");

        List<InventoryItem> items = itemService.findAll();
        uiModel.addAttribute("InventoryItems", items);

        logger.info("No. of inventory items: " + items.size());

        return "items/list";
    }

    
    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(InventoryItem item, BindingResult bindingResult, Model uiModel, 
		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
		Locale locale) {
    	
        logger.info("Creating Inventory Item");
        
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "contact_save_fail");
             uiModel.addAttribute("InventoryItem", item);  
            return "items/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", "Item save success");
        logger.info("Inventory Item id: " + item.getItemId());

        itemService.save(item);
        return "redirect:/items/";  // disable this for sprint 1
    }

        
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
    	InventoryItem item = new InventoryItem();
        uiModel.addAttribute("InventoryItem", item);

        return "items/create";
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
