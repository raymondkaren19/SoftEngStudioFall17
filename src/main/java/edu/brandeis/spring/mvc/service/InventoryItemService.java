package edu.brandeis.spring.mvc.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.brandeis.spring.mvc.domain.InventoryItem;

public interface InventoryItemService {
	List<InventoryItem> findAll();
	InventoryItem findById(Long id);
	InventoryItem save(InventoryItem item);
	   
	    
}
