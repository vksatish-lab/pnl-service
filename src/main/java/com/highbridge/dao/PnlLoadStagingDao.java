package com.highbridge.dao;

import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.domain.PnlLoadStagging;
import com.highbridge.domain.service.PnlLoadStagingRepositoryService;
import com.highbridge.model.PnlLoadRestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PnlLoadStagingDao {

    PnlLoadStagingRepositoryService pnlLoadStagingRepositoryService;

    @Autowired
    public PnlLoadStagingDao(PnlLoadStagingRepositoryService pnlLoadStagingRepositoryService) {
        this.pnlLoadStagingRepositoryService = pnlLoadStagingRepositoryService;
    }

    /**
     * Method to load data to stagging table
     *
     * @param pnlLoadRequest
     * @param request
     */
    public void saveToStaging(PnlLoadRequest pnlLoadRequest, PnlLoadRestRequest request) {
        // use repo service to insert in DB
        return;
    }

    public List<PnlLoadStagging> getStagingRecords(Long requestId) {
        // select * from PnlLoadStaging where requestId = <requestId>
        List<PnlLoadStagging> staggingList = new ArrayList<>();
        return staggingList;
    }
}
