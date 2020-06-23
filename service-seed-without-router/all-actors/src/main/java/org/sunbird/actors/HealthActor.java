package org.sunbird.actors;

import akka.actor.ActorRef;
import org.sunbird.Application;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

@ActorConfig(
  tasks = {"health"},
  dispatcher = "user-dispatcher",
  asyncTasks = {}
)
public class HealthActor extends BaseActor {

  @Override
  public void onReceive(Request request) {

    logger.info("onReceive method call started {}", request.toString());
    ActorRef appleActor = Application.getInstance().getActorRef("sayApple");

    Response response = new Response();
    response.put("Response", request.getRequest());
    response.put("healthy", true);

    // All ask or tell to other actors must happen before sender.tell
    //        if (null != appleActor) {
    //            request.setOperation("apple");
    //            appleActor.tell(request, self());
    //        } else {
    //            logger.info("Can't talk to apple actor");
    //        }

    response.setId("healthId");
    logger.info("onReceive method call - akkadlog1");
    sender().tell(response, self());
  }
}
