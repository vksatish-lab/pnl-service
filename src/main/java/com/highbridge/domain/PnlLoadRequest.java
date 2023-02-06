package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
public class PnlLoadRequest {
    @Id
    Long id;  // This is UUID and request id
    String uploadedBy;  // This is user id
    LoadRequestStatus status;
    RequestCompletionStatusReason requestCompletionStatusReason;  // Will be available only when request is complete.
    Instant requestedOn;  // This value determines which entry takes priority while updating tables. last one always wins
    Instant createdOn;
    Instant lastUpdatedOn;
    int version;

}
