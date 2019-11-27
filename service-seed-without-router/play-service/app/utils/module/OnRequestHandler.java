package utils.module;


import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

import org.apache.log4j.Logger;

import play.http.ActionCreator;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
/**
 * This class will be called on each request.
 * any request pre-filter can be done here.
 * @author manzarul
 *
 */
public class OnRequestHandler implements ActionCreator {
	Logger logger = Logger.getLogger(OnRequestHandler.class);
  @Override
  public Action createAction(Http.Request request, Method method) {
	    return new Action.Simple() {
	      @Override
	      public CompletionStage<Result> call(Context context) {
	        CompletionStage<Result> result = null;
	        logger.debug("On request method called");
	        result = delegate.call(context);
	        return result.thenApply(res -> res.withHeader("Access-Control-Allow-Origin", "*"));
	      }
	    };
	  }



}