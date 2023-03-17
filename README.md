# HrApplication

Repository for a beta Hr Employee registration application Spring Boot project using Microservices
This project contains 3 microservices Employee-Ms, Company-Ms, Holiday-Ms which can be registered on 
Eureka server getting their configurations from Config server available on Git. Services can communicate 
with each other via openFeign through a gateway. Employee-MS and Company-Ms have initDb service funcion 
which adds some data to the databses to be able to test the rest API-s. All 3 Microservices have their 
own seaparted databases. The application contains 2 profiles dev-test, and production using H2 and 
Postgres Db-s. If one of the services are down application will redirect to a fallback method and url.

You can run the services starting with:

    Eureka-Server
    HrApplicationGateway
    Config-Server
    Company-Ms
    Employee-Ms
    Holiday-Ms

After running each microservices you have an opportunity to register new companies, you can add employees 
to these companies, setting their holiday requests, getting them approved or rejected, additionally there 
are several searching and filtering opportunities checking saleries, positions, entering date, names ect.. 
Rest-API end points are accessable can be tested using postman as this project does not include Security.
