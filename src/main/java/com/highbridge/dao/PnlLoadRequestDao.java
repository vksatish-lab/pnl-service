package com.highbridge.dao;

import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.model.PnlLoadRestRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PnlLoadRequestDao {

    public PnlLoadRequest saveLoadRequest(PnlLoadRestRequest pnlLoadRestRequest) {
        return null;
    }

    public Optional<PnlLoadRequest> getLatestUnprocessedLoadRequest(){
        // select * from PnlLoadRequest where status = 'PENDING' sort by requestedOn desc limit 1
        return Optional.empty();
    }
}
