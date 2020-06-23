package org.sunbird.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import org.sunbird.BaseException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request implements Serializable {

  private static final long serialVersionUID = -2362783406031347676L;

  private String id;
  private String ver;
  private String ts;
  private RequestParams params;
  private String operation;
  private String requestId;
  private Map<String, Object> request = new WeakHashMap<>();
  private Map<String, Object> headers = new WeakHashMap<>();

  private int timeout; // in seconds

  protected Map<String, Object> context = new WeakHashMap<>();
  protected String path;

  public Request() {
    this.params = new RequestParams();
  }

  public Request(String operation) {
    this.params = new RequestParams();
    setOperation(operation);
  }

  public String getRequestId() {
    return requestId;
  }

  public Map<String, Object> getContext() {
    return context;
  }

  public void setContext(Map<String, Object> context) {
    this.context = context;
  }

  /** @return the requestValueObjects */
  public Map<String, Object> getRequest() {
    return request;
  }

  public void setRequest(Map<String, Object> request) {
    this.request = request;
  }

  public Object get(String key) {
    return request.get(key);
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public void put(String key, Object vo) {
    request.put(key, vo);
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public void copyRequestValueObjects(Map<String, Object> map) {
    if (null != map && map.size() > 0) {
      this.request.putAll(map);
    }
  }

  @Override
  public String toString() {
    return "Request ["
        + (context != null ? "context=" + context + ", " : "")
        + (request != null ? "requestValueObjects=" + request : "")
        + "]";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVer() {
    return ver;
  }

  public void setVer(String ver) {
    this.ver = ver;
  }

  public String getTs() {
    return ts;
  }

  public void setTs(String ts) {
    this.ts = ts;
  }

  public RequestParams getParams() {
    return params;
  }

  public void setParams(RequestParams params) {
    this.params = params;
    if (this.params.getMsgid() == null && requestId != null) this.params.setMsgid(requestId);
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) throws BaseException {
    this.timeout = timeout;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
