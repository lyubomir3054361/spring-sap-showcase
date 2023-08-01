# Spring-Sap-Showcase
Showcase for Integration of Spring Boot Application into an SAP-environment, using
SAP-HANA-Database.


# Deployment to the HANA-cloud

Create Manifest file manifest.yml-file

        ---
        applications:
          name: spring-sap-showcase
          host: <hana-database-instance>
          path: target/springsapshowcase-1.0.0.jar
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

