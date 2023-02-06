package com.highbridge.domain.service;

import com.highbridge.domain.Pnl;
import com.highbridge.domain.repository.PnlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PnlRepositoryServiceImpl implements PnlRepositoryService {
    @Autowired
    PnlRepository pnlRepository;

    @Override
    public Pnl save(Pnl pnl) {
        return pnlRepository.save(pnl);
    }

    @Override
    public List<Pnl> saveAll(List<Pnl> pnls) {
        return pnlRepository.saveAll(pnls);
    }
}
