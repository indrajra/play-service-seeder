package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.sunbird.message.IResponseMessage;
import org.sunbird.message.Localizer;
import org.sunbird.response.Response;
import play.mvc.Result;
import utils.JsonKey;

public class BaseControllerTest extends TestHelper {
  Localizer localizer = Localizer.getInstance();

  public static String jsonify(Object response) {
    try {
      return new ObjectMapper().writeValueAsString(response);
    } catch (Exception e) {
      return JsonKey.EMPTY_STRING;
    }
  }

  @Test
  public void testJsonifyResponseSuccess() {
    Response response = new Response();
    response.put(JsonKey.MESSAGE, localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null));
    String jsonifyResponse = jsonify(response);
    assertEquals(
        "{\"id\":null,\"ver\":null,\"ts\":null,\"params\":null,\"result\":{\"message\":\"Process failed,please try again later.\"},\"responseCode\":200}",
        jsonifyResponse);
  }

  @Test
  public void testJsonifyResponseFailure() {
    Response response = new Response();
    response.put(JsonKey.MESSAGE, response.getResult());
    String jsonifyResponse = jsonify(response);
    assertEquals(StringUtils.EMPTY, jsonifyResponse);
  }

  @Test
  public void testUnknownRouteFails() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/unknown", "POST", reqMap, headerMap);
    assertTrue(
        getResponseStatus(result) == javax.ws.rs.core.Response.Status.NOT_FOUND.getStatusCode());
  }
}
