package controllers;

import controllers.TestHelper;
import org.junit.Test;
import play.mvc.Result;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HealthControllerTest extends TestHelper {
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
}