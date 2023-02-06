package com.highbridge.domain.service;

import com.highbridge.domain.Pnl;

import java.util.List;

public interface PnlRepositoryService {
    Pnl save(Pnl pnl);

    List<Pnl> saveAll(List<Pnl> pnls);

}
