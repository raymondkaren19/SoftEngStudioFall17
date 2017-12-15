package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.brandeis.spring.mvc.domain.PurchaseOrders;

public interface PurchaseOrdersService {
    List<PurchaseOrders> findAll();
    List<PurchaseOrders> findByPurchaseOrderId();
    PurchaseOrders findById(Long id);
    PurchaseOrders save(PurchaseOrders purchaseOrders);
    Page<PurchaseOrders> findAllByPage(Pageable pageable);
    void setPurchaseOrderId(long id);
}

