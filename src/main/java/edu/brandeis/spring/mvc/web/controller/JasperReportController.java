package edu.brandeis.spring.mvc.web.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
 

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.brandeis.spring.mvc.domain.*;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.service.InventoryItemService;
import edu.brandeis.spring.mvc.service.PurchaseOrderHeaderService;

 
@Controller
@RequestMapping("/report")
public class JasperReportController {
	
	private JasperReport sampleReport;
	private AuditLogService auditLogService;
	private InventoryItemService itemService;
	private PurchaseOrderHeaderService purchaseOrderHeaderService;
	
	private final Logger logger = LoggerFactory.getLogger(JasperReportController.class);
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String list(Model uiModel) {
	        logger.info("Listing report types");

	        ArrayList < String> rptTypes = new ArrayList<String>();
	        rptTypes.add("Inventory");
	        rptTypes .add("PurchaseOrders");
	        
	        uiModel.addAttribute("reportTypes", rptTypes);

	        logger.info("No. of report types: " + rptTypes.size());

	        return "report/list";
	        
	    }

 

@RequestMapping(value = "/inventory", method = RequestMethod.GET)
public void generateReport(HttpServletRequest request,HttpServletResponse response) throws ParseException {
	try {
		auditLogService.saveData("Generate", "Inventory report has been generated", "Admin");
	InputStream inputStream = null;
	
	inputStream = request.getSession().getServletContext().getResourceAsStream ("/WEB-INF/jasper/"  + "InventoryReport.jrxml");
	
	JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
	JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	
	
	HashMap parameters = new HashMap();
	
	List<InventoryItem> items = itemService.findAll();
	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(items);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	
	logger.info("saved pdf");
	
	response.setContentType("application/pdf");
	response.setHeader("Content-disposition", "inline;filename=InventoryReport.pdf");
	OutputStream outputStream = null; 
	try {
		outputStream = response.getOutputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

	
	
} catch (JRException ex) {

	logger.info("exception: " + ex);
logger.debug("exception: " + ex);
}
}


@RequestMapping(value = "/purchaseOrder", method = RequestMethod.GET)
public void generatePurchaseOrderReport(HttpServletRequest request,HttpServletResponse response) throws ParseException {
	try {
		
	auditLogService.saveData("Generate", "Purchase Order Header report has been generated", "Admin");
	InputStream inputStream = null;
	
	inputStream = request.getSession().getServletContext().getResourceAsStream ("/WEB-INF/jasper/"  + "POReport.jrxml");
	
	JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
	JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	
	
	HashMap parameters = new HashMap();	
	
	List<PurchaseOrderHeader> items = purchaseOrderHeaderService.findAll();
	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(items);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	
	logger.info("saved pdf");
	
	response.setContentType("application/pdf");
	response.setHeader("Content-disposition", "inline;filename=PurchaseOrderReport.pdf");
	OutputStream outputStream = null; 
	try {
		outputStream = response.getOutputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);		
	
} catch (JRException ex) {
	logger.info("exception: " + ex);
logger.debug("exception: " + ex);
}
}


@Autowired
public void setInventoryService(InventoryItemService itemService) {
    this.itemService = itemService;
}
    @Autowired
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }
    
    @Autowired
    public void setPurchaseOrderHeaderService(PurchaseOrderHeaderService purchaseOrderHeaderService) {
        this.purchaseOrderHeaderService = purchaseOrderHeaderService;
    }         
 
}