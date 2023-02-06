package com.highbridge.domain.service;

import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.domain.repository.PnlLoadRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PnlLoadRequestRepositoryServiceImpl implements PnlLoadRequestRepositoryService {

    @Autowired
    private PnlLoadRequestRepository pnlLoadRequestRepository;
    @Override
    public PnlLoadRequest save(PnlLoadRequest pnlLoadRequest) {
        return pnlLoadRequestRepository.save(pnlLoadRequest);
    }
}
