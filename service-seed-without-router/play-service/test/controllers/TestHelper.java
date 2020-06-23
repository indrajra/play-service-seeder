package controllers;

import static play.test.Helpers.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang3.StringUtils;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import utils.JsonKey;

/** This a helper class for All the Controllers Test */
public class TestHelper {
  // One and only app
  private static Application app = Helpers.fakeApplication();

  // Let test cases create one if needed. This will be private.
  private final ObjectMapper mapperObj = new ObjectMapper();

  // Only for derivations
  protected Map<String, String> headerMap;

  public TestHelper() {
    headerMap = new WeakHashMap<>();
    headerMap.put(JsonKey.VER, "1.0");
    headerMap.put(JsonKey.ID, "api.test.id");
  }

  /**
   * This method will perform a request call.
   *
   * @param url
   * @param method
   * @param requestMap
   * @param headerMap
   * @return Result
   */
  public Result performTest(String url, String method, Map requestMap, Map headerMap) {
    String data = mapToJson(requestMap);
    Http.RequestBuilder req;
    if (StringUtils.isNotBlank(data) && !requestMap.isEmpty()) {
      JsonNode json = Json.parse(data);
      req = new Http.RequestBuilder().bodyJson(json).uri(url).method(method);
    } else {
      req = new Http.RequestBuilder().uri(url).method(method);
    }
    req.header("Content-Type", "application/json");
    Result result = route(app, req);
    return result;
  }

  /**
   * This method is responsible for converting map to json
   *
   * @param map
   * @return String
   */
  public String mapToJson(Map map) {
    String jsonResp = "";

    if (map != null) {
      try {
        jsonResp = mapperObj.writeValueAsString(map);
      } catch (IOException e) {
      }
    }
    return jsonResp;
  }

  /**
   * This method is used to return the status Code for the perform request
   *
   * @param result
   * @return
   */
  public int getResponseStatus(Result result) {
    return result.status();
  }
}
