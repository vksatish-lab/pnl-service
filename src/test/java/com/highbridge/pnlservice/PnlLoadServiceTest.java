package com.highbridge.pnlservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PnlLoadServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadPnl() {
        // assert payload size to staging table size
        // assert status table is updated
        // verify kafka producer method is invoked
        // in case of error kafka producer is not called.
    }

    @Test
    void getPnlLoadStatus() {
    }

    @Test
    void getAllPendingLoadRequest() {
    }

}