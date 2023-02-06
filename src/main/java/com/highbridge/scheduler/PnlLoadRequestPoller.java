package com.highbridge.scheduler;

import com.highbridge.businesslogic.PnlLoaderBusinessLogic;
import com.highbridge.dao.PnlLoadRequestDao;
import com.highbridge.domain.PnlLoadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class PnlLoadRequestPoller {

    PnlLoadRequestDao loadRequestDao;
    PnlLoaderBusinessLogic pnlLoaderBusinessLogic;


    private static final Logger LOGGER = Logger.getLogger(PnlLoadRequestPoller.class.getName());

    @Autowired
    public PnlLoadRequestPoller(PnlLoadRequestDao requestDao) {
        this.loadRequestDao = requestDao;
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedRateTask() {
        LOGGER.info("Polling for any open load request ");
        Optional<PnlLoadRequest> requestOptional = loadRequestDao.getLatestUnprocessedLoadRequest();
        if (requestOptional.isEmpty()) {
            // No open request to process
            return;
        }
        LOGGER.info("Processing load for - " + requestOptional.get().getId());
        pnlLoaderBusinessLogic.processLoadRequest(requestOptional.get());

    }
}
