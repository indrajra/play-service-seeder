package org.sunbird;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ActorConfig(
        tasks = {"health"},
        dispatcher = "",
        asyncTasks = {}
)
public class HealthActor extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        try {
            String uuid = UUID.randomUUID().toString();
            Map<String, Object> mdc;
            mdc = new HashMap<>();
            mdc.put("requestId",uuid );
            log.setMDC(mdc);
            log.info("onReceive method call started {}", request.toString());

            Response response = new Response();
            response.put("Response",request.getRequest());
            response.put("healthy",true);
            log.info("onReceive method call End ");
            sender().tell(response,self());
        } finally {
            log.clearMDC();
        }

    }
}
