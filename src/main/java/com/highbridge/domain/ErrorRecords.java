package com.highbridge.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ErrorRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long requestId;
    String ticker;
    int quantity;
    double price;
    String securityType;
    double pnl;
    String errorReason;
}
