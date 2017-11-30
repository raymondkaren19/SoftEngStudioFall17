package edu.brandeis.spring.mvc.web.controller;

import java.io.IOException;
import edu.brandeis.spring.mvc.domain.*;
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
import edu.brandeis.spring.mvc.service.*;

@RequestMapping("/supplier")
@Controller
public class SupplierController {
    private final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    private SupplierService supplierService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing Suppliers");

        List<Supplier> suppliers = supplierService.findAll();
        uiModel.addAttribute("Suppliers", suppliers);

        logger.info("No. of suppliers: " + suppliers.size());

        return "supplier/list";
    } 

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(Supplier supplier, BindingResult bindingResult, Model uiModel, 
		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, 
		Locale locale) {
        logger.info("Creating Supplier");
        
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", "supplier_save_fail");            		
            uiModel.addAttribute("supplier", supplier); 
            return "supplier/addSupplier";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", "Supplier save success");
        logger.info("Supplier name: " + supplier.getName());

        supplierService.save(supplier);
        return "redirect:/inventory/";  // disable this for sprint 1
    }

    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
    	Supplier supplier = new Supplier();
        uiModel.addAttribute("supplier", supplier);

        return "supplier/addSupplier";
    }
    
    @Autowired
    public void setInventoryService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
