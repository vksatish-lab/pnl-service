package com.highbridge.dao;

import com.highbridge.domain.Pnl;
import com.highbridge.domain.service.PnlRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PnlDao {

    //JPA Handle
    PnlRepositoryService pnlRepositoryService;

    @Autowired
    public PnlDao(PnlRepositoryService pnlRepositoryService) {
        this.pnlRepositoryService = pnlRepositoryService;
    }

    @Autowired
    public List<Pnl> getPnlData() {
        // select * from Pnl
        return Collections.EMPTY_LIST;
    }

    public void savePnls(List<Pnl> pnls) {
        // save in batched format
        // for larger number of rows this can be split and run in multithreaded format
    }

    public void updatePnls(List<Pnl> pnls) {
        // save in batched format
        // for larger number of rows this can be split and run in multithreaded format
    }

    public void deletePnls(List<Pnl> pnls) {
        // save in batched format
        // for larger number of rows this can be split and run in multithreaded format
    }
}
