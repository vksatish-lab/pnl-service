CREATE TABLE PnlLoadRequest  (
    id VARCHAR(36) PRIMARY KEY, --uuid
    uploadedBy VARCHAR(20),  -- userID
    status VARCHAR(30),    -- PENDING, IN_PROGRESS COMPLETED
    statusReason VARCHAR(30),  -- SUCCESS,PARTIAL_LOAD,ERROR
    requestedOn DATE,
    createdOn DATE,
    lastUpdatedOn TIMESTAMP
);