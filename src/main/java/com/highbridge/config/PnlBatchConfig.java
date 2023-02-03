package com.highbridge.config;

import com.highbridge.batch.PnlStagingReader;
import com.highbridge.batch.PnlWriter;
import com.highbridge.domain.PnlLoadStagging;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class PnlBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PnlWriter pnlWriter;

    @Autowired
    public PnlStagingReader pnlStagingReader;

    @StepScope
    @Bean("pnlDataReader")
    public JdbcPagingItemReader<PnlLoadStagging> pnStagingItemReader() {
        return pnlStagingReader.getPaginationReader();
    }

    private ItemWriter itemWriter;

    @StepScope
    @Bean("pnlDataWriter")
    public ItemWriter<PnlLoadStagging> pnlItemWriter() {
        return pnlWriter;
    }

    @Bean("pnlLoaderStep")
    public Step pnlLoaderStep(@Autowired @Qualifier("pnlDataReader") JdbcPagingItemReader<PnlLoadStagging> pnStagingItemReader) {
        return stepBuilderFactory.get("pnlStep")
                .<PnlLoadStagging, PnlLoadStagging>chunk(1)
                .reader(pnStagingItemReader)
                .writer(pnlItemWriter()).build();
    }

}
