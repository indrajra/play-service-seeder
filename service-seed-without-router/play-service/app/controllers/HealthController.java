package controllers;

import java.util.concurrent.CompletionStage;
import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;

/** This controller class will responsible to check health of the services. */
public class HealthController extends BaseController {
  // Service name must be "service" for the DevOps monitoring.
  private static final String service = "service";

  @Override
  protected boolean validate(Request request) {
    return true;
  }

  /**
   * This action method is responsible for checking Health.
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getHealth() {
    Request req = new Request("health"); // Get API
    return handleRequest(req);
  }

  /**
   * This action method is responsible to check service health
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getServiceHealth(String serviceName, Http.Request req) {
    Request request = createSBRequest(req);
    request.getContext().put("service", serviceName);
    request.setOperation("health");
    return handleRequest(request);
  }
}
