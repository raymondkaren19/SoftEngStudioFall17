package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.brandeis.spring.mvc.domain.AuditLog;

public interface AuditLogService {
    List<AuditLog> findAll();
    AuditLog findById(Long id);
    AuditLog save(AuditLog aLog);
    void saveData(String event, String detail, String modifiedBy);
    Page<AuditLog> findAllByPage(Pageable pageable);
}
