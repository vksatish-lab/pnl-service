package com.highbridge.batch;

import com.highbridge.domain.PnlLoadStagging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("pnlWriter")
@StepScope
public class PnlWriter implements ItemWriter<PnlLoadStagging> {

    private static final Logger log = LoggerFactory.getLogger(PnlWriter.class);

    @Override
    public void write(List<? extends PnlLoadStagging> items) throws Exception {
        log.info("Inside PNL writer with following data");
        /** For every record in this list perform following operation -

         1. Check if the record exits in table
         2. switch(record status)
             case 'new':
                   prepare insert query;
                    return
              case 'update':
                     check if the existing record is ahead of incomming record using asOf date and compare it against request asOf date
                      if valid record prepare for update
                         else logg and skip
                        return;
         3. Perform DB operation
         4. For any error add them to error table
         **/

        log.info("Records persisted in DB");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        // At the end of batch update status on status table based on number of error records
        return ExitStatus.COMPLETED;
    }
}
