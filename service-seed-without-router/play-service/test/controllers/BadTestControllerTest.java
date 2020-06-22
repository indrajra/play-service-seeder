package controllers;

import org.junit.Test;
import play.mvc.Result;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BadTestControllerTest extends TestHelper {
    @Test
    public void testPostClientExceptionPasses() {
        Result result = performTest("/exception", "POST", null, headerMap);
        assertEquals(Response.Status.fromStatusCode(500).getStatusCode(), getResponseStatus(result));
    }

    @Test
    public void testPostServerExceptionPasses() {
        Map<String, String> reqMap = new WeakHashMap<>();
        reqMap.put("operation", "unknownOperation");
        Result result = performTest("/exception", "POST", reqMap, headerMap);
        assertTrue(getResponseStatus(result) == Response.Status.fromStatusCode(500).getStatusCode());
    }
}