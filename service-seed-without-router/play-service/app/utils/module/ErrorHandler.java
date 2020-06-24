package utils.module;

import com.typesafe.config.Config;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;

/** This class will be called when exception is not handle by application. */
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
    logger.error("Error handler called");
  }
}
