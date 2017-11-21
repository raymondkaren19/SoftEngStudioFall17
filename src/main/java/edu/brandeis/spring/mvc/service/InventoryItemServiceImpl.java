package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.repository.InventoryItemRepository;
import edu.brandeis.spring.mvc.domain.InventoryItem;

@Repository
@Transactional
@Service("inventoryItemService")
public class InventoryItemServiceImpl implements InventoryItemService {
    private InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional(readOnly=true)
    public List<InventoryItem> findAll() {
        return Lists.newArrayList(inventoryItemRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public InventoryItem findById(Long id) {
        return inventoryItemRepository.findOne(id);
    }

    @Override
    public InventoryItem save(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }

    @Autowired
    public void setInventoryItemRepository(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<InventoryItem> findAllByPage(Pageable pageable) {
        return inventoryItemRepository.findAll(pageable);
    }   

}
