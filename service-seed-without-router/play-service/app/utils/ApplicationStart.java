package utils;


import org.sunbird.Application;
import play.api.Environment;
import play.api.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

/**
 * This class will be called after on application startup. only one instance of this class will be
 * created. StartModule class has responsibility to eager load this class.
 *
 */
@Singleton
public class ApplicationStart {
	  /**
	   * All one time initialization which required during server startup will fall here.
	   * @param lifecycle ApplicationLifecycle
	   * @param environment Environment
	   */
	  @Inject
	  public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment) {
	  	//instantiate actor system and initialize all the actors
		  Application.getInstance().init();
	    // Shut-down hook
	    lifecycle.addStopHook(
	        () -> {
	          return CompletableFuture.completedFuture(null);
	        });
	  }
}
