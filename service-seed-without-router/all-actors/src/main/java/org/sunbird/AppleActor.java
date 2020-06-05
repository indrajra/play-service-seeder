package org.sunbird;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

import javax.swing.text.DefaultEditorKit;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;


@ActorConfig(
        tasks = {"sayApple"},
        dispatcher = "",
        asyncTasks = {}
)
public class AppleActor extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        Instant start = new Date().toInstant();
        logger.info("Before waiting...");
        while (Duration.between(start, new Date().toInstant()).toMillis() < 2000);

            logger.info("sayApple after delay");
            Response response = new Response();
            response.setId("appleId");
            response.put("sayApple", true);
            sender().tell(response, self());
    }
}
