package edu.brandeis.spring.mvc.web.controller;

import edu.brandeis.spring.mvc.domain.*;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.brandeis.spring.mvc.service.*;
import edu.brandeis.spring.mvc.web.util.UrlUtil;

@RequestMapping("/supplier")
@Controller
public class SupplierController {
    private final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    private SupplierService supplierService;
    private MessageSource messageSource;
    private AuditLogService auditLogService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing Suppliers");

        List<Supplier> suppliers = supplierService.findAll();
        uiModel.addAttribute("Suppliers", suppliers);

        logger.info("No. of suppliers: " + suppliers.size());

        return "supplier/list";
    } 

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public String show(@PathVariable("ID") Long id, Model uiModel) {
        Supplier supplier = supplierService.findById(id);
        uiModel.addAttribute("supplier", supplier);

        return "supplier/showSupplier";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{ID}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Supplier supplier, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating supplier: " + supplier.getID());
        if (bindingResult.hasErrors() || null == supplier.getID()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("supplier_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("supplier", supplier);
            return "supplier/addSupplier";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("supplier_save_success", new Object[]{}, locale)));
        supplierService.save(supplier);
        
        auditLogService.saveData("Update", "Supplier Updated", "Admin");
        
        return "redirect:/inventory/" + UrlUtil.encodeUrlPathSegment(supplier.getID().toString(),
                httpServletRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{ID}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("ID") Long id, Model uiModel) {
        uiModel.addAttribute("supplier", supplierService.findById(id));
        return "supplier/addSupplier";
    }

    @PreAuthorize("isAuthenticated()")
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
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("supplier_save_success", new Object[]{}, locale)));

        logger.info("Supplier name: " + supplier.getName());

        supplierService.save(supplier);
        return "redirect:/inventory/";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
    	Supplier supplier = new Supplier();
        uiModel.addAttribute("supplier", supplier);

        return "supplier/addSupplier";
    }

    @ResponseBody  
    @RequestMapping(value = "/supplierlistgrid", method = RequestMethod.GET, produces="application/json")
    public SupplierGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows,
                                 @RequestParam(value = "sidx", required = false) String sortBy,
                                 @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing suppliers for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing suppliers for grid with sort: {}, order: {}", sortBy, order);

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

        Page<Supplier> supplierPage = supplierService.findAllByPage(pageRequest);

        // Construct the grid data that will return as JSON data
        SupplierGrid supplierGrid = new SupplierGrid();

        supplierGrid.setCurrentPage(supplierPage.getNumber() + 1);
        supplierGrid.setTotalPages(supplierPage.getTotalPages());
        supplierGrid.setTotalRecords(supplierPage.getTotalElements());

        supplierGrid.setSupplierData(Lists.newArrayList(supplierPage.iterator()));

        return supplierGrid;
    }

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
