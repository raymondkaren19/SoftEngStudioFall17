package edu.brandeis.spring.mvc.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.brandeis.spring.mvc.domain.PurchaseOrderHeader;

public interface PurchaseOrderHeaderService {
    List<PurchaseOrderHeader> findAll();
    PurchaseOrderHeader findById(Long id);
    PurchaseOrderHeader save(PurchaseOrderHeader purchaseOrderHeader);
    Page<PurchaseOrderHeader> findAllByPage(Pageable pageable);
}
