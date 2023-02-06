package com.highbridge.businesslogic;

import com.highbridge.dao.PnlLoadRequestDao;
import com.highbridge.dao.PnlLoadStagingDao;
import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.model.PnlLoadRestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PnlLoadServiceBusinessLogic {

    PnlLoadRequestDao pnlLoadRequestDao;
    PnlLoadStagingDao pnlLoadStagingDao;
    PnlServiceAndRequestTemplate pnlServiceAndRequestTemplate;

    @Autowired
    public PnlLoadServiceBusinessLogic(PnlLoadRequestDao pnlLoadRequestDao,
                                       PnlLoadStagingDao pnlLoadStagingDao,
                                       PnlServiceAndRequestTemplate pnlServiceAndRequestTemplate) {
        this.pnlLoadRequestDao = pnlLoadRequestDao;
        this.pnlLoadStagingDao = pnlLoadStagingDao;
        this.pnlServiceAndRequestTemplate = pnlServiceAndRequestTemplate;
    }

    @Transactional
    public PnlLoadRequest processIncomingRequest(PnlLoadRestRequest pnlLoadRestRequest) {
        // Perform step 1 & 2 in transaction.
        //1.  add records to status table
        PnlLoadRequest request = pnlLoadRequestDao.saveLoadRequest(pnlLoadRestRequest);
        //2.  copy records to staging table
        pnlLoadStagingDao.saveToStaging(request, pnlLoadRestRequest);
        return request;
    }

}
