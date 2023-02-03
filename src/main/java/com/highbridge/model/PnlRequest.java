package com.highbridge.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PnlRequest {

    @NotEmpty(message = "Ticker must not be empty")
    String ticker;

    @NotNull
    int quantity;

    @NotNull
    double price;

    @NotNull
    String securityType;

    @NotNull
    double pnl;
}
