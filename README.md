# Read Me 

This is system design with pseudo code for building backend of PNL Refresh. 

# Getting Started

Execute main class to run application - PnlServiceApplication.java 

### System Design 

Given 
1. Each load on PNL should update the previous ones

Assumptions
1. Pnl load is initiated via a user on web.
2. Client initiating the request has the data to be loaded.

Technology Stack 
1. Rest Services - Java/SpringBoot 
2. Database - Relational - Postgresql (Opensource)
3. App Server container - Embedded Tomcat as part of JAR file.
5. Scheduler - Spring scheduler.
6. For service to be on clustered we will be having load balancer ahead of services. 
7. Front React/Next.js based application deployed on node server.  (Why next.js - gives library overs plain vanilla react enabling faster time to market)

For cloud based solution - 
1. Services could be deployed to EC2 instance
2. Database on AWS RDS

* Enabling the async processing on backend enables for -
  * 1. Future load
    2. If we need to pivot the design from client initiated data to self extract and load 


Here is the system design -
![Alt text](design.png?raw=true "Title")

### FAQ 

1. What are different server side error handling available ?
   * All request parameter will be validated using javax.validation for basic data quality. 
   * Failed records will be captured in ErrorRecord table for it to replayed back to user

2. How do we ensure that last data will always take precedence  ?
  * Every incoming request would be will be captured with <b>requestedOn</b> or <b>asOf</b> data indicating timestamp of request and data associated with it.
  * <b>Poller only picks up <b>most recent</b> request to process if in case there are multiple pending request to process. </b>
  * At the time of database operation following things will happen in order -
    * For every record determine if its an insert or update or delete
      *     Insert - If the record is source but not in master PNL table
      *     Update - if the record is source with latestimestap as compared to master PNL TABLE 
      *     Delete - if the record is not in source but present in master PNL TABLE 

    * For Delete mark the rows as soft deleted using isDeleted flag
    * The version field on each row should help clustered environment to ensure and preventing servers working from stale copy.

3. How does the design handles zoombie rows or stale rows or closed positions? 
  * <b>Any records which not in input payload but in master will be terminated as part of refresh request </b>

4. What is your testing strategy -
   * Junit testing for all backend code 
   * Use H2 in memory database for local testing 
   * Spring-boot-starter-test to test spring wiring. 

5. Provide Sample code for request/response 
   *  All request & response are captured in package  - [com.highbridge.model](src%2Fmain%2Fjava%2Fcom%2Fhighbridge%2Fmodel)

6. On server side do we need to pull all data in memory from staging and master at once to comparison ?
   * Given the volume of data this should be not be an Issue.
   * The solution could also be implemented via simply using three different query to directly join tables and perform update, delete and insert.I continued with this solution assuming if there are any enrichment
   or business logic we need to perform we can do it.
   * If we encounter increase in volume solution could be batched & multi-threaded as required. 

7. Client Implementation
   * Client would be implemented using React/Next.js framework deployed on Node server.

## Table Schema 
All table schema can be found at - [schema-all.sql](src%2Fmain%2Fresources%2Fschema-all.sql) . Also shared below -

````
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


````

# Clarification from HB
* [Assumption] I am assuming the position file would roughly have around 5-10 columns and for approx 10K rows positions file won't exceed in worst case scenario 2MB.
the client is not uploading a file unless that???s your solution to the problem.
[HB] I provided the fields/attributes/properties we expect to be in the PnL record in the test instructions


* [Question] Expect the process to 4 times a day ? Is it safe to assume this will be user(Human) initiated activity by uploading some file on a website?
web or desktop based client will trigger the action depending on your solution

* *[Question] Is it safe to assume a position file in CSV format will be uploaded to some website by the user for refreshing position values?
[HB] I leave the format to you but it???s not necessarily a file.

* [Question] Would like to confirm we can???t use any messaging framework(custom build or out of the box solutions like Kafka/AWS SQS) infrastructure here.
??? Don???t use a solution that that will require special ports or sockets to be opened between client and server.  If you are going to use an RPC based solution, please make sure that it satisfies this requirement.
[HB] you can use kafka or AWS SQS if you think it satisfies the above requirement.  we will ask you about specifics on how this is done if either is your preferred solution.  For example, how will kafka/awsSQS communicate with a web/native client?

* [Assumption] It is not valid if you only managed to upload 9.9k(actually 9999) out of 10k records.  I am assuming in case of any row that???s faile we need to revert that particular record back to the user.
[HB] correct it???s not valid.  how you decide to handle this scenario is part of the test

* [Question] For record milestoning can we assume each position in file to have asof column indicating date & time this row was last valid or we can derive that based on time of upload?
[HB] your solution determines the answer to this question

* [Assumption] Position will be uniquely identified by - Account Number, Product Key & PositionDate
[HB]         your solution determines the answer to this question.  it???s not critical to solving the problem.

* [Question] - Do we have a use case to retrieve position as of particulate datetime -i.e do we need to milestone old records or the table at any point should only have active(most recent) records.
[HB]        there???s no need to do historical searches

* As the data will be loaded four times in a day - do we assume order as they arrived, first payload to the server as first and the one that came at last as fourth one? Else do I assume I can add additional fields in the payload which could indicate the same.
[HB] the 4 loads are independent and will contain the full set of data you need for that load.  there is no need for the 4th run to know about the first run.  treat each load as a ???rerun???

* Can we get more than 4 files in one business day? And if we do, should we ignore them or always process the last one?
[HB] please plan for only 4.  The solution shouldn???t really matter on the number of iterations.  Again, don???t assume it is a file upload unless that is the payload you decide is best for your solution.

* Also is there any event or trigger which initiates this load activity? Is it a human forced trigger on the client , or a side effect of something changing in a client  or time scheduled event?
[HB] assume users will click a button on the UI

* Finally for the source of  PnL payload is it expected to be uploaded to the system or is it to be extracted based on trigger? Or this could be solutionized either way?
the source of the pnl payload is out of scope for this test.  assume it is available in the client before they click the button.

