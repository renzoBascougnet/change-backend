package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.entity.RequestLog;
import com.renzoBascougnet.change_backend.repository.RequestLogRepository;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository requestLogRepository;

    @Override
    @Async
    public void saveRequestLog(RequestLog requestLog){
        log.info(requestLogRepository.save(requestLog).toString());
    }

    @Override
    public Page<RequestLog> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("timestamp")));
        return requestLogRepository.findAll(pageable);
    }
}
