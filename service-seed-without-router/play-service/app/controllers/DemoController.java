package controllers;

import org.sunbird.BaseException;
import org.sunbird.request.Request;
import play.mvc.Result;
import utils.RequestMapper;

import java.util.concurrent.CompletionStage;

/**
 * this controller will help you in understanding the process of passing request to Actors with operation.
 */
public class DemoController extends BaseController {

    /**
     * this action method will send the request with operation name 'demoTask' to actor(all-actors) package
     * URI for this action method is configured in conf->routes file
     * @return CompletionStage of Result
     * @throws BaseException
     */
    public CompletionStage<Result> demoTask() throws BaseException {
        Request request=(Request) RequestMapper.mapRequest(request(),Request.class);
        return handleRequest(request,"demoTask");
    }

}
