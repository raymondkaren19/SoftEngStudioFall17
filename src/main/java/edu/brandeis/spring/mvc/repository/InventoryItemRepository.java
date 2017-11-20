package edu.brandeis.spring.mvc.repository;

import java.util.List;

import edu.brandeis.spring.mvc.domain.*;

import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
    List<InventoryItem> findAll();
}
