package edu.brandeis.spring.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.repository.InventoryItemRepository;
import edu.brandeis.spring.mvc.repository.PurchaseOrdersRepository;
import edu.brandeis.spring.mvc.domain.InventoryItem;
import edu.brandeis.spring.mvc.domain.PurchaseOrders;

@Repository
@Transactional
@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {
    private PurchaseOrdersRepository purchaseOrdersRepository;
    private long purchaseOrderId;

    @Override
    @Transactional(readOnly=true)
    public List<PurchaseOrders> findAll() {
        return Lists.newArrayList(purchaseOrdersRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public List<PurchaseOrders> findByPurchaseOrderId() {
        ArrayList<PurchaseOrders> allOrders = Lists.newArrayList(purchaseOrdersRepository.findAll());
        ArrayList<PurchaseOrders> foundOrders = new ArrayList<PurchaseOrders>();

        for (PurchaseOrders purchaseOrder : allOrders) {
            if (purchaseOrder.getPurchaseOrderId() == purchaseOrderId)
                foundOrders.add(purchaseOrder);
        }

        return foundOrders;
    }

    @Override
    @Transactional(readOnly=true)
    public PurchaseOrders findById(Long id) {
        return purchaseOrdersRepository.findOne(id);
    }

    @Override
    public PurchaseOrders save(PurchaseOrders purchaseOrders) {
        return purchaseOrdersRepository.save(purchaseOrders);
    }

    @Autowired
    public void setPurchaseOrdersRepository(PurchaseOrdersRepository purchaseOrdersRepository) {
        this.purchaseOrdersRepository = purchaseOrdersRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<PurchaseOrders> findAllByPage(Pageable pageable) {
        return purchaseOrdersRepository.findAll(pageable);
    }   

    @Override
    public void setPurchaseOrderId(long id) {
        this.purchaseOrderId = id;
    }   

}
