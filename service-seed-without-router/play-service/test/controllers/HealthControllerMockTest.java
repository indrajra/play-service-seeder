package controllers;

import static org.junit.Assert.assertEquals;

import akka.actor.ActorRef;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Http;
import play.mvc.Result;
import scala.concurrent.Await;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class, BaseController.class, ActorRef.class, Await.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class HealthControllerMockTest extends TestHelper {

  @Test
  public void testOnServerHandlerPasses() {
    setupMock();
    HealthController healthController = Mockito.mock(HealthController.class);
    PowerMockito.when(healthController.createSBRequest(Mockito.any(Http.Request.class)))
        .thenThrow(new RuntimeException("induced due to buggy server impl"));

    Result result = performTest("/service/health", "GET", null, headerMap);
    System.out.println(getResponseStatus(result));
    assertEquals(Response.Status.fromStatusCode(500).getStatusCode(), getResponseStatus(result));
  }
}
