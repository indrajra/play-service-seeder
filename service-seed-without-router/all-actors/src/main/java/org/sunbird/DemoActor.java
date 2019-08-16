package org.sunbird;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;


@ActorConfig(
        tasks = {"demoTask"},
        dispatcher = "=",
        asyncTasks = {}
)
public class DemoActor extends BaseActor {
    @Override
    public void onReceive(Request request) throws Throwable {
        Response response=new Response();
        response.put("message","Hello I am demo actor");
        sender().tell(response,self());
    }
}
