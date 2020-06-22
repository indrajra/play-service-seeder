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
 *
 */
public class ResponseHandler {

    /**
     * This method will handle all the failure response of Api calls.
     *
     * @param exception
     * @return
     */
    private static Result handleFailureResponse(Object exception, Request request) {
        Response response = ResponseFactory.getFailureMessage(exception, request);
        return Results.internalServerError(Json.toJson(response));
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
