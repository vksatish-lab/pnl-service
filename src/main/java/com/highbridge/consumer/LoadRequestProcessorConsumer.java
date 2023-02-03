package com.highbridge.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer to receive message to trigger spring batch process to load data to PNL syste.
 */
@Component
public class LoadRequestProcessorConsumer {

    @KafkaListener(topics = "pnl-load-request", groupId = "group-id")
    public void listen(String requestId) {
        System.out.println("Received request in group - group-id: " + requestId);
        // trigger spring batch process for load in DB and capture error
        // at the end of processing update status and error table.
    }
}
