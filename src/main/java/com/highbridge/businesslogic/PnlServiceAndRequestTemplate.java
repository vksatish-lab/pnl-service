package com.highbridge.businesslogic;

import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.domain.PnlLoadStagging;
import com.highbridge.domain.service.PnlLoadRequestRepositoryService;
import com.highbridge.domain.service.PnlLoadStagingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PnlServiceAndRequestTemplate {
    @Autowired
    PnlLoadStagingRepositoryService pnlLoadStagingRepositoryService;
    @Autowired
    PnlLoadRequestRepositoryService pnlLoadRequestRepositoryService;

    @Transactional
    public void execute(PnlLoadStagging pnlLoadStagging, PnlLoadRequest pnlLoadRequest) {
        pnlLoadStagingRepositoryService.save(pnlLoadStagging);
        pnlLoadRequestRepositoryService.save(pnlLoadRequest);

    }
}
