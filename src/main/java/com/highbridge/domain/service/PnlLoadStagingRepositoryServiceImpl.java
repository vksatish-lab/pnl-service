package com.highbridge.domain.service;

import com.highbridge.domain.PnlLoadStagging;
import com.highbridge.domain.repository.PnlLoadStaggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PnlLoadStagingRepositoryServiceImpl implements PnlLoadStagingRepositoryService {

    @Autowired
    PnlLoadStaggingRepository pnlLoadStaggingRepository;
    @Override
    public PnlLoadStagging save(PnlLoadStagging pnlLoadStaggin) {
        return pnlLoadStaggingRepository.save(pnlLoadStaggin);
    }
}
