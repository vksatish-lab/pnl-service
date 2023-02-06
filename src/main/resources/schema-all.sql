CREATE TABLE PnlLoadRequest  (
    id BIGINT PRIMARY KEY, --uuid
    uploadedBy VARCHAR(20),  -- userID
    status VARCHAR(30),    -- PENDING, IN_PROGRESS COMPLETED
    statusReason VARCHAR(30),  -- SUCCESS,PARTIAL_LOAD,ERROR
    requestedOn DATE,
    createdOn DATE,
    lastUpdatedOn TIMESTAMP
);


CREATE TABLE PnlLoadStaging  (
    id BIGINT PRIMARY KEY,
    ticker VARCHAR(20),
    quantity  double,
    price double,
    securityType VARCHAR(30),
    pnl double,
    loadRequestId VARCHAR(36) -- ref to PnlLoadRequest
);

CREATE TABLE ErrorRecords  (
    id BIGINT PRIMARY KEY,
    ticker VARCHAR(20),
    securityType VARCHAR(30),
    quantity  double,
    price double,
    requestId VARCHAR(36), -- ref to PnlLoadRequest
    errorReason VARCHAR(100)
);

CREATE TABLE Pnl  (
    id BIGINT PRIMARY KEY,
    ticker VARCHAR(20),
    quantity  double,
    price double,
    securityType VARCHAR(30),
    pnl double,
    batchId BIGINT, --Reference to request ID based on client request
    lastUpdate TIMESTAMP,
    isDeleted boolean
);








