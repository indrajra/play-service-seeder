package controllers;

import org.sunbird.BaseException;
import org.sunbird.request.Request;
import play.mvc.Result;
import utils.RequestMapper;

import java.util.concurrent.CompletionStage;

public class DemoController extends BaseController {

    public CompletionStage<Result> demoTask() throws BaseException {
        Request request=(Request) RequestMapper.mapRequest(request(),Request.class);
        return handleRequest(request,"demoTask");
    }

}
