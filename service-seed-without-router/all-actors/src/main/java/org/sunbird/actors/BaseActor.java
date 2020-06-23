package org.sunbird.actors;

import akka.actor.UntypedAbstractActor;
import akka.event.DiagnosticLoggingAdapter;
import akka.event.Logging;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.sunbird.ActorServiceException;
import org.sunbird.BaseException;
import org.sunbird.HelloWord;
import org.sunbird.message.Localizer;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

public abstract class BaseActor extends UntypedAbstractActor {
  final String msgId = "msgId";

  final DiagnosticLoggingAdapter logger = Logging.getLogger(this);

  public abstract void onReceive(Request request) throws Throwable;

  protected Localizer localizer = Localizer.getInstance();

  @Override
  public void onReceive(Object message) throws Throwable {
    Map<String, Object> trace = new HashMap<>();
    if (message instanceof Request) {
      Request request = (Request) message;
      String operation = request.getOperation();

      if (request.getHeaders().containsKey("msgId")) {
        ArrayList<String> requestIds = (ArrayList<String>) request.getHeaders().get(msgId);
        trace.put(msgId, requestIds.get(0));
        logger.setMDC(trace);
      }

      try {
        startTrace(operation);
        onReceive(request);
        new HelloWord(logger.getMDC()).printHello();
        endTrace(operation);
      } catch (Exception e) {
        logger.error("{} : message : {} {}", operation, e.getMessage(), e);
        onReceiveException(operation, e);
      } finally {
        logger.clearMDC();
      }
    } else {
      Response res = (Response) message;
      logger.info(" onReceive called with invalid type of request " + res.getId());
    }
  }

  /**
   * this method will handle the exception
   *
   * @param callerName
   * @param exception
   * @throws Exception
   */
  protected void onReceiveException(String callerName, Exception exception) throws Exception {
    logger.error(
        "Exception in message processing for: "
            + callerName
            + " :: message: "
            + exception.getMessage(),
        exception);
    sender().tell(exception, self());
  }

  /**
   * this message will handle the unsupported actor operation
   *
   * @param callerName
   */
  protected void onReceiveUnsupportedMessage(String callerName) {
    logger.info(callerName + ": unsupported operation");
    /**
     * TODO Need to replace null reference from getLocalized method and replace with requested
     * local.
     */
    BaseException exception = new ActorServiceException.InvalidOperationName(null);
    sender().tell(exception, self());
  }

  /**
   * this is method is used get message in different different locales
   *
   * @param key
   * @param locale
   * @return
   */
  protected String getLocalizedMessage(String key, Locale locale) {
    return localizer.getMessage(key, locale);
  }

  /**
   * This method will return the current timestamp.
   *
   * @return long
   */
  public long getTimeStamp() {
    return System.currentTimeMillis();
  }

  /**
   * This method we used to print the logs of starting time of methods
   *
   * @param tag
   */
  public void startTrace(String tag) {
    logger.info(String.format("%s started at %s", tag, getTimeStamp()));
  }

  /**
   * This method we used to print the logs of ending time of methods
   *
   * @param tag
   */
  public void endTrace(String tag) {
    logger.info(String.format("%s ended at %s", tag, getTimeStamp()));
  }
}
