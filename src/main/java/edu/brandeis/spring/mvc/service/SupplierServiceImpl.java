package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.repository.SupplierRepository;
import edu.brandeis.spring.mvc.domain.Supplier;

@Repository
@Transactional
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Supplier> findAll() {
        return Lists.newArrayList(supplierRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public Supplier findById(Long id) {
        return supplierRepository.findOne(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Autowired
    public void setSupplierRepository(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<Supplier> findAllByPage(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }
}