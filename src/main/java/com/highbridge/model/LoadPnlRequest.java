package com.highbridge.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class LoadPnlRequest {

    @NotEmpty(message = "List cannot be empty for load")
    List<PnlRequest> pnls;
}
