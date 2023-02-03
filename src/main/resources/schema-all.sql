
CREATE TABLE PnlLoadStatus  (
    id VARCHAR(36) PRIMARY KEY, --uuid
    uploadedBy VARCHAR(20),  -- userID
    status VARCHAR(30),
    statusReason VARCHAR(30),
    requestedOn DATE,
    createdOn DATE,
    lastUpdatedOn DATE
);

CREATE TABLE PnlLoadStaging  (
    uuid VARCHAR(36) PRIMARY KEY,
    ticker VARCHAR(20),
    securityType VARCHAR(30),
    quantity  double,
    price double,
    loadRequestId VARCHAR(36) -- ref to PnlLoadStatus
);

CREATE TABLE Pnl  (
    uuid VARCHAR(36) PRIMARY KEY,
    ticker VARCHAR(20),
    securityType VARCHAR(30),
    quantity  double,
    price double,
    asOf DATE --last refreshed on
);

CREATE TABLE ErrorRecords  (
    uuid VARCHAR(36) PRIMARY KEY,
    ticker VARCHAR(20),
    securityType VARCHAR(30),
    quantity  double,
    price double,
    requestId VARCHAR(36), -- ref to PnlLoadStatus
    errorReason VARCHAR(100)
);
