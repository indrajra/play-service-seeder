package org.sunbird.actors;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;

@ActorConfig(
  tasks = {"throwException"},
  dispatcher = "user-dispatcher",
  asyncTasks = {}
)
public class BadActor extends BaseActor {

  @Override
  public void onReceive(Request request) {
    logger.info("onReceive method call started {}", request.toString());
    throw new RuntimeException("badActor throwing wilfully...");
  }
}
