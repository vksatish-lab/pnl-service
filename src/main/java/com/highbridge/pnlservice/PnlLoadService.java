package com.highbridge.pnlservice;

import com.highbridge.domain.LoadRequestStatus;
import com.highbridge.domain.RequestCompletionStatusReason;
import com.highbridge.model.LoadPnlRequest;
import com.highbridge.model.LoadPnlResponse;
import com.highbridge.model.PnlLoadStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Rest controllers providing API to refresh PNL data and also view current status of it.
 */
@RestController
public class PnlLoadService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final static String TOPIC_NAME = "TOPIC_NAME";

    /**
     * Initiate request to refresh PNL records
     * @param loadPnlRequest
     * @return
     */
    @PostMapping("/pnl/load")
    public LoadPnlResponse loadPnl(@RequestBody LoadPnlRequest loadPnlRequest) {
        //1. Seed the request in status table
        //2. Load the data in staging table
        //3. Add a kafka message for consumer to pick up and load
        LoadPnlResponse response = new LoadPnlResponse();
        response.setRequestId(UUID.randomUUID().toString()); // This is request if from Step #1 above
        // publish kafka message
        sendMessage(response.getRequestId());
        return response;
    }

    /**
     * Fetch status of particular load request id.
     * @param requestId
     * @return PnlLoadStatusResponse
     */
    @GetMapping("/pnl/load-status")
    public PnlLoadStatusResponse getPnlLoadStatus(@RequestParam(name = "requestId") String requestId) {
        // fetch the result from status table based on requestID
        PnlLoadStatusResponse response = new PnlLoadStatusResponse();
        response.setRequestId(UUID.randomUUID().toString()); // This is request if from Step #1 above
        response.setRequestStatus(LoadRequestStatus.COMPLETED);
        response.setRequestStatusReason(Optional.of(RequestCompletionStatusReason.PARTIAL_LOAD));
        return response;
    }

    /**
     * This API can be used by UI to determine if there are any in-flight request which if its there it could
     * disable option on UI to load any new request. This could be also used to check for any dup request.
     *
     * @return
     */
    @GetMapping("/pnl/load-status/all")
    public List<PnlLoadStatusResponse> getAllPendingLoadRequest() {
        // fetch the result from status for all pending status.
        PnlLoadStatusResponse response = new PnlLoadStatusResponse();
        response.setRequestId(UUID.randomUUID().toString()); // This is request if from Step #1 above
        response.setRequestStatus(LoadRequestStatus.PENDING);
        return Arrays.asList(response);
    }

    private void sendMessage(String msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
    }
}