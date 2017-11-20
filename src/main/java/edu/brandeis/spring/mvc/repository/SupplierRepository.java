package edu.brandeis.spring.mvc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.brandeis.spring.mvc.domain.*;
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> findAll();
}
