package com.renzoBascougnet.change_backend.service.impl;

import com.renzoBascougnet.change_backend.dto.RequestLogResponse;
import com.renzoBascougnet.change_backend.entity.RequestLog;
import com.renzoBascougnet.change_backend.repository.RequestLogRepository;
import com.renzoBascougnet.change_backend.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository requestLogRepository;

    @Override
    @Async
    public void saveRequestLog(RequestLog requestLog){
        requestLogRepository.save(requestLog);
    }

    @Override
    public Page<RequestLogResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("timestamp")));
        Page<RequestLog> requestLogs = requestLogRepository.findAll(pageable);

        return requestLogs.map(requestLog -> RequestLogResponse.builder()
                .timestamp(requestLog.getTimestamp())
                .endpoint(requestLog.getEndpoint())
                .parameters(requestLog.getParameters())
                .response(requestLog.getResponse())
                .error(requestLog.isError())
                .build());
    }
}
