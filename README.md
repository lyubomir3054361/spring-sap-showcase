# Spring-SAP-Showcase
Showcase for demonstration of Integration of a Spring Boot Application-instance into an SAP-environment,
in order to perform CRUD-operations on a SAP-HANA-database

## Database setup

1. [Register for an SAP-Trial-account](https://developers.sap.com/tutorials/hcp-create-trial-account.html)
2. [Deploy an SAP-HANA-instance](https://developers.sap.com/tutorials/hana-cloud-deploying.html)
3. Create the following table-schema:


    CREATE TABLE ECM_JOB_EXECUTION_STATUS(
    JOB_ID VARCHAR(500),
    JOB_NAME VARCHAR(500),
    START_TIME VARCHAR(500),
    STATUS VARCHAR(500),
    RESULT VARCHAR(500));

## Deployment of a new server-instance

Configure the application.properties:

    database.url=jdbc:sap://<hana-instance-host>:443?encrypt=true&validateCertificate=true&traceFile=stdout&traceOptions=CONNECTIONS,API,STATISTICS,CLEANERS,TIMESTAMPS,ELAPSEDTIMES
    database.username=<username>
    database.password=<password>
    
    spring.datasource.driver-class-name=com.sap.db.jdbc.Driver
    spring.datasource.url=jdbc:sap://<hana-instance-host>:443?encrypt=true&validateCertificate=true&traceFile=stdout&traceOptions=CONNECTIONS,API,STATISTICS,CLEANERS,TIMESTAMPS,ELAPSEDTIMES
    
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.HANAColumnStoreDialect
    
    spring.datasource.username=<username>
    spring.datasource.password=<password>

    spring.autoconfigure.exclude[0]=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
    spring.autoconfigure.exclude[1]=org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration




Create a manifest.yml-file inside root directory:


        ---
        applications:
          name: spring-sap-showcase-server
          host: spring-sap-showcase-server
          path: target/spring-sap-showcase-server-1.0.0.jar
          domain: <custom-domain>
          memory: 1G
          env:
            JBP_CONFIG_OPEN_JDK_JRE: '{ jre: { version: 17.+ }}'
            JBP_CONFIG_SPRING_AUTO_RECONFIGURATION: '{enabled: false}'


Build the application locally
```bash 
  mvn clean install
```

Make sure cf is installed
```bash 
  brew install cloudfoundry/tap/cf-cli@8
```

Set API-endpoint (can be retrieved from the trial subaccount overview)
```bash 
  cf login <api-endpoint-url> <username> <password>
```

Deploy the application to the cloud
```bash 
  cf push
```
## Job execution

1. Open dev-space of the trial sub-account overview
2. Select the deployed instance
3. Open the link of the application route
4. add **/test_native_sql** to the url and press **ENTER**
