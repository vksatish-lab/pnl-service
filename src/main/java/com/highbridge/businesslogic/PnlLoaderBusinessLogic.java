package com.highbridge.businesslogic;

import com.highbridge.dao.ErrorRecordDao;
import com.highbridge.dao.PnlDao;
import com.highbridge.dao.PnlLoadRequestDao;
import com.highbridge.dao.PnlLoadStagingDao;
import com.highbridge.domain.ErrorRecords;
import com.highbridge.domain.Pnl;
import com.highbridge.domain.PnlLoadRequest;
import com.highbridge.domain.PnlLoadStagging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class PnlLoaderBusinessLogic {

    PnlDao pnlDao;
    PnlLoadStagingDao pnlLoadStagingDao;
    PnlLoadRequestDao pnlLoadRequestDao;
    ErrorRecordDao errorRecordDao;

    private static final Logger LOGGER = Logger.getLogger(PnlLoaderBusinessLogic.class.getName());

    @Autowired
    public PnlLoaderBusinessLogic(PnlDao pnlDao, PnlLoadStagingDao pnlLoadStagingDao,
                                  PnlLoadRequestDao pnlLoadRequestDao,
                                  ErrorRecordDao errorRecordDao) {
        this.pnlDao = pnlDao;
        this.pnlLoadStagingDao = pnlLoadStagingDao;
        this.pnlLoadRequestDao = pnlLoadRequestDao;
        this.errorRecordDao = errorRecordDao;
    }

    @Transactional
    public void processLoadRequest(PnlLoadRequest pnlLoadRequest) {
        List<ErrorRecords> errorRecords = loadPnl(pnlLoadRequest);
        saveErrorRecords(errorRecords);
        updateRequestStatusTable(errorRecords, pnlLoadRequest);
    }

    private List<ErrorRecords> loadPnl(PnlLoadRequest pnlLoadRequest) {
        LOGGER.info("Loading PNL data for request - " + pnlLoadRequest.getId());
        // Get the data from staging for given requestId
        List<PnlLoadStagging> stagingList = pnlLoadStagingDao.getStagingRecords(pnlLoadRequest.getId());
        // get the data from PNL master data
        List<Pnl> pnls = pnlDao.getPnlData();
        List<Pnl> insertPnls = createListForInsert(stagingList, pnls);
        List<Pnl> updatePnls = createListForUpdate(stagingList, pnls, pnlLoadRequest);
        List<Pnl> deletePnls = createListForDelete(stagingList, pnls);
        pnlDao.savePnls(insertPnls);
        pnlDao.updatePnls(updatePnls);
        pnlDao.deletePnls(deletePnls);
        return Collections.emptyList();
    }

    private void saveErrorRecords(List<ErrorRecords> errorRecords) {
        errorRecordDao.saveErrorRecords(errorRecords);
    }

    private void updateRequestStatusTable(List<ErrorRecords> errorRecords, PnlLoadRequest pnlLoadRequest) {
        if (errorRecords.isEmpty()) {
            // update status as SUCCESS and reason as COMPLETE
        } else {
            // update status as SUCCESS with reason as PARTIAL_LOAD
        }
    }

    private List<Pnl> createListForInsert(List<PnlLoadStagging> stagingList, List<Pnl> pnls) {
        //1.  filter the staging list where ID is not in list pnls
        //2. for those element construct the list
        return Collections.emptyList();
    }

    private List<Pnl> createListForUpdate(List<PnlLoadStagging> stagingList, List<Pnl> pnls, PnlLoadRequest request) {
        //1.  Get the list of staging rows which are also in pnls
        //2. for those element select out only those records in PNL whose timestamp is less than request timestamp
        //3. return the list
        return Collections.emptyList();
    }

    private List<Pnl> createListForDelete(List<PnlLoadStagging> stagingList, List<Pnl> pnls) {
        //1.  Get the list of rows in PNL which are not in
        //2. for those element construct the list
        return Collections.emptyList();
    }
}
