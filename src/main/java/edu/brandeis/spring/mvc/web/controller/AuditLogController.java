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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.domain.InventoryItem;
import edu.brandeis.spring.mvc.domain.AuditLog;
import edu.brandeis.spring.mvc.service.AuditLogGrid;
import edu.brandeis.spring.mvc.service.InventoryItemService;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.service.Message;
import edu.brandeis.spring.mvc.service.SupplierService;
import edu.brandeis.spring.mvc.web.util.UrlUtil;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/audit")
@Controller
public class AuditLogController {
	private final Logger logger = LoggerFactory.getLogger(AuditLogController.class);
	
	// String userName = "NotSet";

    private AuditLogService auditLogService;
    // private InventoryItemService itemService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing Audit Log items");

        List<AuditLog> items = auditLogService.findAll();
        uiModel.addAttribute("audititems", items);

        logger.info("No. of audit items: " + items.size());
        
        return "audit/list";
    }
    
    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public List<AuditLog> listData() {
        return auditLogService.findAll();
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    @ResponseBody
    public AuditLog create(@RequestBody AuditLog audit) {
        logger.info("Creating audit: " + audit);
        auditLogService.save(audit);
        logger.info("Audit item created successfully with info: " + audit);
        return audit;
    }
    
    
    @ResponseBody  
    @RequestMapping(value = "/auditlistgrid", method = RequestMethod.GET, produces="application/json")
    public AuditLogGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows,
                                      @RequestParam(value = "sidx", required = false) String sortBy,
                                      @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing items for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing items for grid with sort: {}, order: {}", sortBy, order);

        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("eventType")) {
            orderBy = "eventType";
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

        Page<AuditLog> itemPage = auditLogService.findAllByPage(pageRequest);

        // Construct the grid data that will return as JSON data
        AuditLogGrid itemGrid = new AuditLogGrid();

        itemGrid.setCurrentPage(itemPage.getNumber() + 1);
        itemGrid.setTotalPages(itemPage.getTotalPages());
        itemGrid.setTotalRecords(itemPage.getTotalElements());

        itemGrid.setItemData(Lists.newArrayList(itemPage.iterator()));

        return itemGrid;
    }
    
    /*
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal ) {

        userName = principal.getName(); //get logged in username
        model.addAttribute("username", userName);
        return "hello";

    }
    */
   

    @Autowired
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }
   
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
