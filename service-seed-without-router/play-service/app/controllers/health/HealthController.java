package controllers.health;

import controllers.BaseController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;
import play.mvc.Results;

/**
 * This controller class will responsible to check health of the services.
 *
 * @author Anmol
 */
public class HealthController extends BaseController {
  // Service name must be "service" for the devops monitoring.
  private static final String service = "service";

  /**
   * This action method is responsible for checking Health.
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getHealth() {
    CompletionStage<Result> response = handleRequest();
    return response;
  }

  /**
   * This action method is responsible to check user-service health
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getUserOrgServiceHealth(String health) {
    CompletableFuture<String> cf = new CompletableFuture<>();
    cf.complete(dummyResponse);
    return service.equalsIgnoreCase(health)
        ? cf.thenApplyAsync(Results::ok)
        : cf.thenApplyAsync(Results::badRequest);
  }
}
