package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRecords {
    String requestId;
    String ticker;
    int quantity;
    double price;
    String securityType;
    double pnl;
    String errorReason;
}
