package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.repository.PurchaseOrderHeaderRepository;
import edu.brandeis.spring.mvc.repository.SupplierRepository;
import edu.brandeis.spring.mvc.domain.PurchaseOrderHeader;
import edu.brandeis.spring.mvc.domain.PurchaseOrders;
import edu.brandeis.spring.mvc.domain.Supplier;

@Repository
@Transactional
@Service("purchaseOrderHeaderService")
public class PurchaseOrderHeaderServiceImpl implements PurchaseOrderHeaderService {
    private PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;

    @Override
    @Transactional(readOnly=true)
    public List<PurchaseOrderHeader> findAll() {
        return Lists.newArrayList(purchaseOrderHeaderRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public PurchaseOrderHeader findById(Long id) {
        return purchaseOrderHeaderRepository.findOne(id);
    }

    @Override
    public PurchaseOrderHeader save(PurchaseOrderHeader purchaseOrderHeader) {
        return purchaseOrderHeaderRepository.save(purchaseOrderHeader);
    }

    @Autowired
    public void setPurchaseOrderHeaderRepository(PurchaseOrderHeader purchaseOrderHeader) {
        this.purchaseOrderHeaderRepository = purchaseOrderHeaderRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<PurchaseOrderHeader> findAllByPage(Pageable pageable) {
        return purchaseOrderHeaderRepository.findAll(pageable);
    }
}