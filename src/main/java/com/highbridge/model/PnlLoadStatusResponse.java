package com.highbridge.model;

import com.highbridge.domain.ErrorRecords;
import com.highbridge.domain.LoadRequestStatus;
import com.highbridge.domain.RequestCompletionStatusReason;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class PnlLoadStatusResponse {
    String requestId;
    LoadRequestStatus requestStatus;
    Optional<RequestCompletionStatusReason> requestStatusReason;
    List<ErrorRecords> errorRecords;  // in case of error load all error records
}
