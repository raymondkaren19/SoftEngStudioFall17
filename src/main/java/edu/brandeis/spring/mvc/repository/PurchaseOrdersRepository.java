package edu.brandeis.spring.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.brandeis.spring.mvc.domain.*;

public interface PurchaseOrdersRepository extends PagingAndSortingRepository<PurchaseOrders, Long> {
}
