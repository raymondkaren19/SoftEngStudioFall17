package edu.brandeis.spring.mvc.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.brandeis.spring.mvc.domain.AuditLog;

@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLog, Long>  {

}
