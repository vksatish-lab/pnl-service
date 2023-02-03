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
2. Database - Relational - MySQL (Opensource)
3. App Server container - Embedded Tomcat as part of JAR file.
4. Messaging - Apache Kafka for async processing on backend server. 
5. Batch Processing - Spring Batch.
6. For service to be on clustered we will be having load balancer ahead of services. 
7. Front React/Next.js based application deployed on node server.  (Why next.js - gives library overs plain vanilla react enabling faster time to market)

For cloud based solution - 
1. Services could be deployed to EC2 instance
2. Database on AWS RDS
3. For Messaging AWS SQS

* Enabling the async processing on backend enables for -
  * 1. Future load
    2. If we need to pivot the design from client initiated data to self extract and load 

All table schema can be found at - [schema-all.sql](src%2Fmain%2Fresources%2Fschema-all.sql)

Here is the system design -
![Alt text](design.jpeg?raw=true "Title")

### FAQ 

1. What are different server side error handling available ?
   * All request parameter will be validated using javax.validation for basic data quality. 
   * Failed records will be captured in ErrorRecord table for it to replayed back to user

2. How do we ensure that last data will always take precedence  ?
  * Every incoming request would be will be captured with <b>requestedOn</b> or <b>asOf</b> data indicating timestamp of request and data associated with ti.
  * At the time of database operation following things will happen in order -
    * For every record determine is its an insert or update
    * If its insert perform plan insert
    * If its an Update check for position asOf date and compare it against incomming position asOfDate if its less update it else ignore the update as this would be stale update
    * The version field on each row should help clustered enviornment to ensure and preventing servers working from stale copy.

3. How does the design handles zoombie rows or stale rows or closed positions? 
  * <b>Assumption</b> In case of closing of any position an assumption is made that client has to send reversal or new updated position with O PNL. Upon receiving of such reversal the record should get updated with DB with values a 0. For audit purpose we would still track that row instead of deleting it with value as zero.

4. What is your testing strategy -
   * Junit testing for all backend code 
   * Use H2 in memory database for local testing 
   * Spring-boot-starter-test to test spring wiring. 

5. Provide Sample code for request/response 
   *  All request & response are captured in package  - [com.highbridge.model](src%2Fmain%2Fjava%2Fcom%2Fhighbridge%2Fmodel)

6. Client Implementation
  * Client would be implemented using React/Next.js framework deployed on Node server.


TODO Items: 
1. Passing reference data across spring batch process. 

