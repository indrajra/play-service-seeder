package controllers;

import java.util.concurrent.CompletionStage;
import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;

/** This controller class will responsible to check health of the services. */
public class BadTestController extends BaseController {
  public CompletionStage<Result> throwException(Http.Request req) {
    Request request = createSBRequest(req);
    if (request.getOperation() != null) {
      return handleRequest(request);
    } else {
      return handleRequest(request);
    }
  }
}
