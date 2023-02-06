package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
public class PnlLoadStagging {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id; // uuid
    String requestId;
    String ticker;
    int quantity;
    double price;
    String securityType;
    double pnl;
    int version;
    Instant asOf;
}
