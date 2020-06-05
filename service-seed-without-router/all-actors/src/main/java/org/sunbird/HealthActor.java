package org.sunbird;

import akka.actor.ActorRef;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;


@ActorConfig(
        tasks = {"health"},
        dispatcher = "",
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
        logger.info("onReceive method call End ");

        response.setId("healthId");
        sender().tell(response, self());

        if (null != appleActor) {
            request.setOperation("apple");
            appleActor.tell(request, self());
        } else {
            logger.info("Can't talk to apple actor");
        }
    }
}
