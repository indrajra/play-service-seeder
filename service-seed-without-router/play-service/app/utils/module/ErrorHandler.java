package utils.module;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunbird.response.Response;

import com.typesafe.config.Config;

import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

/**
 * This class will be called when exception is not handle by application.
 * @author manzarul
 */

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @Inject
    public ErrorHandler(
            Config config,
            Environment environment,
            OptionalSourceMapper sourceMapper,
            Provider<Router> routes) {
        super(config, environment, sourceMapper, routes);
    }

    @Override
    public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable t) {
        logger.info(
                "Global: onError called for path = "
                        + request.path()
                        + ", headers = "
                        + request.getHeaders().toMap());
        Response response = new Response();
        response.setResponseCode(500);
        response.put("message", "server error");
        return CompletableFuture.completedFuture(Results.internalServerError(Json.toJson(response)));
    }
}
