package com.highbridge.domain.service;

import com.highbridge.domain.ErrorRecords;

import java.util.List;

public interface ErrorRecordsService {
    ErrorRecordsService save(ErrorRecords errorRecords);

    List<ErrorRecordsService> fetchErrorRecordsList();

}
