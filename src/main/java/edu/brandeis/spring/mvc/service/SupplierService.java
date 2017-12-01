package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.brandeis.spring.mvc.domain.Supplier;

public interface SupplierService {
    List<Supplier> findAll();
    Supplier findById(Long id);
    Supplier save(Supplier Supplier);
    Page<Supplier> findAllByPage(Pageable pageable);
}

