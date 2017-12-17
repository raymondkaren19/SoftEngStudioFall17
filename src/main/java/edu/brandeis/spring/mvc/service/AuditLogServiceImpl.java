package edu.brandeis.spring.mvc.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.brandeis.spring.mvc.repository.AuditLogRepository;
import edu.brandeis.spring.mvc.repository.SupplierRepository;
import edu.brandeis.spring.mvc.domain.AuditLog;
import edu.brandeis.spring.mvc.domain.Supplier;

@Repository
@Transactional
@Service("auditLogService")

public class AuditLogServiceImpl implements AuditLogService{
	private AuditLogRepository auditRepository;
	
	private String username = "Default";

    @Override
    @Transactional(readOnly=true)
    public List<AuditLog> findAll() {
        return Lists.newArrayList(auditRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public AuditLog findById(Long id) {
        return auditRepository.findOne(id);
    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        return auditRepository.save(auditLog);
    }
    
    @Override
    public void saveData(String event, String detail, String modifiedBy)
    {
    	AuditLog log = new AuditLog(event,detail, username);
    	save(log);
    			
    }

    @Autowired
    public void setSupplierRepository(AuditLogRepository aLogRepository) {
        this.auditRepository = aLogRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<AuditLog> findAllByPage(Pageable pageable) {
        return auditRepository.findAll(pageable);
    }
    
    public void SetLoggedInUsername(String name)
    {
    	username = name;
    }

}
