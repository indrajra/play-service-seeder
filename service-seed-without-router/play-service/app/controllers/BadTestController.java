package controllers;

import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

/**
 * This controller class will responsible to check health of the services.
 *
 */
public class BadTestController extends BaseController {
  /**
   * This action method is responsible for checking Health.
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> throwException(Http.Request req) {
    Request request = createSBRequest(req);
    if (request.getOperation() != null) {
      return handleRequest(request);
    } else {
      // Expect a client exception
      return handleRequest(null);
    }
  }
}
