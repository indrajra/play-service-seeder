package org.sunbird;

import java.util.Locale;
import org.sunbird.message.IResponseMessage;
import org.sunbird.message.Localizer;

public class ActorServiceException {

  public static class InvalidOperationName extends BaseException {
    public InvalidOperationName(Locale locale) {
      super(
          IResponseMessage.INVALID_OPERATION_NAME,
          Localizer.getInstance().getMessage(IResponseMessage.INVALID_OPERATION_NAME, locale),
          500);
    }
  }

  public static class InvalidRequestTimeout extends BaseException {
    public InvalidRequestTimeout() {
      super(
          IResponseMessage.INVALID_REQUESTED_DATA,
          Localizer.getInstance().getMessage(IResponseMessage.INVALID_REQUESTED_DATA, null),
          500);
    }
  }

  public static class InvalidRequestData extends BaseException {
    public InvalidRequestData() {
      super(
          IResponseMessage.INVALID_REQUESTED_DATA,
          Localizer.getInstance().getMessage(IResponseMessage.INVALID_REQUESTED_DATA, null),
          400);
    }
  }
}
