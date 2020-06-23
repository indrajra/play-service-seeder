package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Http;
import play.mvc.Result;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HealthController.class, BaseController.class})
public class HealthControllerTest extends CommonHelperTest {
  @Test
  public void testGetHealthPasses() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/health", "GET", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }

  @Test
  public void testGetServiceHealthPasses() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/service/health", "GET", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }

  @Test
  public void testPostHealthFails() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("accept", "yes");
    Result result = performTest("/health", "POST", reqMap, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testOnServerHandlerPasses() {
    setupMock();
    HealthController healthController = Mockito.mock(HealthController.class);
    PowerMockito.when(healthController.createSBRequest(Mockito.any(Http.Request.class)))
        .thenThrow(new RuntimeException("induced due to buggy server impl"));

    Map<String, String> reqMap = new WeakHashMap<>();
    reqMap.put("operation", "unknownOperation");
    Result result = performTest("/exception", "POST", reqMap, headerMap);
    assertEquals(Response.Status.fromStatusCode(500).getStatusCode(), getResponseStatus(result));
  }
}
