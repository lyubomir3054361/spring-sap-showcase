# Spring-Sap-Showcase
Showcase for Integration of Spring Boot Application into an SAP-environment, using
SAP-HANA-Cloud.


# Database setup inside SAP HANA Database Explorer

    CREATE TABLE ECM_JOB_EXECUTION_STATUS(
    JOB_ID VARCHAR(500),
    JOB_NAME VARCHAR(500),
    START_TIME VARCHAR(500),
    STATUS VARCHAR(500),
    RESULT VARCHAR(500));

# Deployment to the HANA-cloud

Create Manifest file manifest.yml-file inside root directory

        ---
        applications:
          name: spring-sap-showcase-app
          host: <hana-database-instance>
          path: target/spring-sap-showcase-app-1.0.0.jar
          memory: 1G

Build the application locally
```bash 
  mvn clean install
```

Make sure cf is installed

```bash 
  brew install cloudfoundry/tap/cf-cli@8
```

Set api-endpoint
 
```bash 
  cf login <api-endpoint-url> <username> <password>
```
Deployment of the application to the cloud
```bash 
  cf push
```
