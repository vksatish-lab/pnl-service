package com.highbridge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class Pnl {
    String id; // uuid
    String ticker;
    int quantity;
    double price;
    String securityType;
    double pnl;
    int version;
    Instant asOf;
}
