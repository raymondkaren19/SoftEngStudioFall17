import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.test.*;

import edu.brandeis.spring.mvc.domain.AuditLog;
import edu.brandeis.spring.mvc.web.controller.AuditLogController;
import edu.brandeis.spring.mvc.service.AuditLogService;
import edu.brandeis.spring.mvc.service.AuditLogGrid;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.runner.RunWith;
import org.junit.Test;


public class AuditLogControllerTest {
	 private final List<AuditLog> auditList = new ArrayList<AuditLog>();
	 
	 private MockMvc mockMvc;

	    @Before
	    public void initAuditInfoList() {
	    	AuditLog audit = new AuditLog();
	        audit.setId(1L);
	        audit.setEventType("updated");
	        audit.setModifiedDate("12/04/17");
	        audit.setModifiedBy("Admin");
	        auditList.add(audit);
	    }

	    @Test
	    public void testList() throws Exception {
	        AuditLogService auditService = mock(AuditLogService.class);
	        when(auditService.findAll()).thenReturn(auditList);

	        AuditLogController auditController = new AuditLogController();

	        ReflectionTestUtils.setField(auditController, "auditLogService", auditService);

	        ExtendedModelMap uiModel = new ExtendedModelMap();
	        uiModel.addAttribute("audits", auditController.listData());

	        List<AuditLog> audittemplist = (List<AuditLog>) uiModel.get("audits");

	        assertEquals(1, audittemplist.size());
	    }	    
	    

	    @Test
	    public void testCreate() {
	        final AuditLog newAuditInfo = new AuditLog();
	        newAuditInfo.setId(10L);
	        newAuditInfo.setEventType("deleted");
	        newAuditInfo.setModifiedDate("11/04/17");
	        newAuditInfo.setModifiedBy("John");

	        AuditLogService auditLogService = mock(AuditLogService.class);
	        when(auditLogService.save(newAuditInfo)).thenAnswer(new Answer<AuditLog>() {
	            public AuditLog answer(InvocationOnMock invocation) throws Throwable {
	            	auditList.add(newAuditInfo);
	                return newAuditInfo;
	            }
	        });

	        AuditLogController auditController = new AuditLogController();
	        ReflectionTestUtils.setField(auditController, "auditLogService",
	        		auditLogService);

	        AuditLog audit = auditController.create(newAuditInfo);
	        assertEquals(Long.valueOf(10), audit.getId());
	        assertEquals("deleted", audit.getEventType());
	        assertEquals("John", audit.getModifiedBy());

	        assertEquals(2, auditList.size());
	    }
	    
	    

	    @Test
	    public void testAuditLogObject() {	    	
	    	
	    	AuditLog testItem = new AuditLog("Created", "Item Created", "Admin" );    		    	
	    	
		    assertEquals("Created", testItem.getEventType());
		    assertEquals("Admin", testItem.getModifiedBy());	
		    
		    
		    testItem.setModifiedBy("superuser");
		    assertEquals("superuser", testItem.getModifiedBy());	
	    }
	    
	    @Test
        public void testAuditLogGridObject() {	    	
	    	
	    	AuditLogGrid grid = new AuditLogGrid();    
	    	grid.setCurrentPage(1);
	    	grid.setItemData(auditList);
	    	grid.setTotalPages(1);
	    	grid.setTotalRecords(2);
	    	
		    assertEquals(1, grid.getCurrentPage());
		    assertEquals(1, grid.getTotalPages());	
		    assertEquals(2, grid.getTotalRecords());    	    
		    
	    }
	    
	    @Test
        public void testAuditLogGridList() {	    	
	    	
	    	AuditLogGrid grid = new AuditLogGrid();    
	    	grid.setCurrentPage(1);
	    	
	    	grid.setTotalPages(1);
	    	grid.setTotalRecords(2);
	    	
	    	List<AuditLog> list = grid.getItemData();
	    	
	    	assertNull(list);
	    	
	    	grid.setItemData(auditList);
	    	
	    	list = grid.getItemData();
	    	
	    	assertNotNull(list);
	    		
	    	System.out.println("List size = " + list.size() + " modified by = " + list.get(0).getModifiedBy());
	    	
	    	assertEquals("Admin", list.get(0).getModifiedBy());	    				     	    		    
	    } 
	    
	     
	 
}
