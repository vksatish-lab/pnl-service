package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Optional;

@Getter
@Setter
public class PnlLoadStatus {
    String id;  // This is UUID and request id
    String uploadedBy;  // This is user id
    LoadRequestStatus status;
    Optional<RequestCompletionStatusReason> requestCompletionStatusReason;  // Will be available only when request is complete.
    Instant requestedOn;  // This value determines which entry takes priority while updating tables. last one always wins
    Instant createdOn;
    Instant lastUpdatedOn;
}
