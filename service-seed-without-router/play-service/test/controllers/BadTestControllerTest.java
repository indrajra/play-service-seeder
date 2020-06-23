package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.WeakHashMap;
import javax.ws.rs.core.Response;
import org.junit.Test;
import play.mvc.Result;

public class BadTestControllerTest extends TestHelper {
  @Test
  public void testPostClientExceptionPasses() {
    Result result = performTest("/exception", "POST", null, headerMap);
    assertEquals(Response.Status.fromStatusCode(500).getStatusCode(), getResponseStatus(result));
  }

  @Test
  public void testPostServerExceptionPasses() {
    Map<String, Object> reqMap = new WeakHashMap<>();
    reqMap.put("operation", "unknownOperation");
    reqMap.put("timeout", 10);
    Result result = performTest("/exception", "POST", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.fromStatusCode(500).getStatusCode());
  }
}
