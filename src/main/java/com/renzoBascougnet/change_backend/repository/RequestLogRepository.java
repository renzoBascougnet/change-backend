package com.renzoBascougnet.change_backend.repository;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog,Long> {
    Page<RequestLog> findAll(Pageable pageable);
}
