# HrApplication

Repository for a beta Hr Employee registration application Spring Boot project using Microservices This project contains 3 microservices Employee-Ms, Company-Ms, Holiday-Ms which can be registered on Eureka server getting their configurations from Config server available on Git. Services can communicate with each other via open Feign through a gateway. Employee-MS and Company-Ms have initDb service function which adds some data to the databases to be able to test the rest API-s. All 3 Microservices have their own sea parted databases. The application contains 2 profiles dev-test, and production using H2 and Postgres Db-s. If one of the services are down application will redirect to a fallback method and URL.

You can run the services starting with:

Eureka-Server,
HrApplicationGateway,
Config-Server,
Company-Ms,
Employee-Ms,
Holiday-Ms,

After running each microservice you have an opportunity to register new companies, you can add employees to these companies, setting their holiday requests, getting them approved or rejected, additionally there are several searching and filtering opportunities checking salaries, positions, entering date, names etc.. Rest-API end points are accessible can be tested using postman as this project does not include Security.
