package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.AuditLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> { }
