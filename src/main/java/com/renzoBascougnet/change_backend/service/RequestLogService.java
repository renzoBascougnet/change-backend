package com.renzoBascougnet.change_backend.service;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import org.springframework.data.domain.Page;

public interface RequestLogService {

    void saveRequestLog(RequestLog requestLog);

    Page<RequestLog> findAll(int page, int size);
}
