package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PnlLoadStagging {
    String id; // uuid
    String requestId;
    String ticker;
    int quantity;
    double price;
    String securityType;
    double pnl;
    int version;
    Instant asOf;
}
