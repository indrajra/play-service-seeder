package org.sunbird;

import akka.actor.UntypedAbstractActor;
import org.sunbird.message.IResponseMessage;
import org.sunbird.message.Localizer;
import org.sunbird.message.ResponseCode;
import org.sunbird.request.Request;

import java.util.Locale;

/**
 * @author Amit Kumar
 */
public abstract class BaseActor extends UntypedAbstractActor {

    public abstract void onReceive(Request request) throws Throwable;
    protected Localizer localizer = Localizer.getInstance();

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Request) {
            Request request = (Request) message;
            String operation = request.getOperation();
            ////ProjectLogger.log("BaseActor:onReceive called for operation:" + operation, LoggerEnum.INFO);
            try {
                ////ProjectLogger.log(String.format("%s:%s:method started at %s",this.getClass().getSimpleName(),operation,System.currentTimeMillis()), LoggerEnum.DEBUG);
                onReceive(request);
                ////ProjectLogger.log(String.format("%s:%s:method ended at %s",this.getClass().getSimpleName(),operation,System.currentTimeMillis()), LoggerEnum.DEBUG);
            } catch (Exception e) {
                onReceiveException(operation, e);
            }
        } else {
            //ProjectLogger.log("BaseActor: onReceive called with invalid type of request.", LoggerEnum.INFO);
        }
    }

    /**
     * this method will handle the exception
     * @param callerName
     * @param exception
     * @throws Exception
     */
    protected void onReceiveException(String callerName, Exception exception) throws Exception {
        //ProjectLogger.log("Exception in message processing for: " + callerName + " :: message: " + exception.getMessage(), exception);
        sender().tell(exception, self());
    }


    /**
     * this message will handle the unsupported actor operation
     * @param callerName
     */
    protected void onReceiveUnsupportedMessage(String callerName) {
        //ProjectLogger.log(callerName + ": unsupported operation", LoggerEnum.INFO);
        /**
         * TODO Need to replace null reference from getLocalized method and replace with requested local.
         */
        BaseException exception =
                new ActorServiceException.InvalidOperationName(
                        IResponseMessage.INVALID_OPERATION_NAME,
                        getLocalizedMessage(IResponseMessage.INVALID_OPERATION_NAME,null),
                        ResponseCode.CLIENT_ERROR.getCode());
        sender().tell(exception, self());
    }


    /**
     * this is method is used get message in different different locales
     * @param key
     * @param locale
     * @return
     */

    protected String getLocalizedMessage(String key, Locale locale){
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
        //ProjectLogger.log(String.format("%s:%s:started at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),LoggerEnum.DEBUG.name());
    }

    /**
     * This method we used to print the logs of ending time of methods
     *
     * @param tag
     */
    public void endTrace(String tag) {
        //ProjectLogger.log(String.format("%s:%s:ended at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),LoggerEnum.DEBUG.name());
    }
}
