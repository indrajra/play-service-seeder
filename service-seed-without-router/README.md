## service-seed-without-router
Play, Akka seed project without router implementation.

Use this for creating a play-based service that leverages all internal actors. By default, runs http on port 9000.

### Pre-requisites
0. Make a copy of setVars.sh.sample to list all the environment vars needed.
1. Change all-actors/application.conf `thisActorSystem` to your own actor system name
2. Use the same string in /all-actors/Application.java in the field `actorSystemName`
3. Change the play secret key value in /play-service/application.conf - `play.http.secret.key`

### Note
1.In this Application , throw only org.sunbird.BaseException

### Build

1. Execute clean install `mvn clean install`


### Run 
1. For debug mode, <br> 
   `cd play-service` <br>
   `mvn play2:dist`  <br>
   `mvn play2:run`

2. For run mode, 
   `cd play-service` <br>
   `mvn play2:dist`  <br>
   `mvn play2:start`

### Verify running status

Hit the following Health check curl command 

`curl -X GET \
   http://localhost:9000/health \
   -H 'Postman-Token: 6a5e0eb0-910a-42d1-9077-c46f6f85397d' \
   -H 'cache-control: no-cache'`

And, a successful response must be like this:

`{"id":"api.200ok","ver":"v1","ts":"2019-01-17 16:53:26:286+0530","params":{"resmsgid":null,"msgid":"8e27cbf5-e299-43b0-bca7-8347f7ejk5abcf","err":null,"status":"success","errmsg":null},"responseCode":"OK","result":{"response":{"response":"SUCCESS","errors":[]}}}`
# play-service-seeder

Home to two projects that enable you to create a Play-Akka based microservice. Both of them implement the following endpoints for health service. 
* /health
* /service/health

Logging support for system-wide and api specific will be added in the next revision.

## service-seed-without-router
This play-akka project is primarily intended for intra actor communications, where Akka serialization is not neeeded (internal JVM messaging). The router is absent in this implementation to allow actors creation in any manner you want (say hierarchical).
Source: user-service 

## service-seed-with-router
This play-akka project is intended for inter system actor communications. The default Akka serialization used is Google's protobuf. The 'Message' class holds the unified payload structure for both Request and Response.
Source: user-service and open-saber 

