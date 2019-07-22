# play-service-seeder

Home to two projects that enable you to create a Play-Akka based microservice. Both of them implement the following endpoints for health service. 
* /health
* /service/health
Log system-wide and api specific will be added in the next revision.

## service-seed-without-router
This play-akka project is primarily intended for intra actor communications, where Akka serialization is not neeeded (internal JVM messaging). The router is absent in this implementation to allow actors creation in any manner you want (say hierarchical).
Source: user-service 

## service-seed-with-router
This play-akka project is intended for inter system actor communications. The default Akka serialization used is Google's protobuf. The 'Message' class holds the unified payload structure for both Request and Response.
Source: user-service and open-saber 

