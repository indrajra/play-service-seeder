package controllers;

import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.response.ResponseFactory;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

/**
 * this class is used to handle the request and ask from actor and return response on the basis of
 * success and failure to user.
 */
public class ResponseHandler {

  /**
   * This method will handle all the failure response of Api calls.
   *
   * @param exception
   * @return
   */
  private static Result handleFailureResponse(Object exception, Request request) {
    Result result;
    Response response = ResponseFactory.getFailureMessage(exception, request);
    switch (response.getResponseCode()) {
        //            case HttpStatus.SC_BAD_REQUEST:
        //                result = Results.badRequest(Json.toJson(response));
        //                break;
        //            case HttpStatus.SC_UNAUTHORIZED:
        //                result = Results.unauthorized(Json.toJson(response));
        //                break;
      default:
        result = Results.internalServerError(Json.toJson(response));
        break;
    }
    return result;
  }

  /**
   * this method will divert the response on the basis of success and failure
   *
   * @param object
   * @return
   */
  public static Result handleResponse(Object object, Request request) {
    if (object instanceof Response) {
      return handleSuccessResponse((Response) object);
    }
    return handleFailureResponse(object, request);
  }

  private static Result handleSuccessResponse(Response response) {
    return Results.ok(Json.toJson(response));
  }
}
