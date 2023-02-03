package com.highbridge.domain;

public enum LoadRequestStatus {
    PENDING,  // Request received but not yet processed (in Queue)
    IN_PROGRESS,  // Request currently being processed
    COMPLETED  // Request completed with either success or failure or partial success
}
