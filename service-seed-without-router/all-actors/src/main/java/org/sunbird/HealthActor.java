package org.sunbird;

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
    public void onReceive(Request request) throws Throwable {
            logger.info("onReceive method call started {}", request.toString());
            Response response = new Response();
            response.put("Response", request.getRequest());
            response.put("healthy", true);
            logger.info("onReceive method call End ");
            sender().tell(response, self());
    }
}
