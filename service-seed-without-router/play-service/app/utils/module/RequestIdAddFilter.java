package utils.module;

import akka.stream.Materializer;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import utils.JsonKey;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class RequestIdAddFilter extends Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestIdAddFilter.class);

    @Inject
    public RequestIdAddFilter(Materializer materializer) {
        super(materializer);

    }


    @Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
            Http.RequestHeader requestHeader) {
        long startTime = System.currentTimeMillis();

        Optional<String> requestIdHeader = requestHeader.getHeaders().get(JsonKey.REQUEST_MESSAGE_ID);
        String reqId = requestIdHeader.isPresent() ? requestIdHeader.get() : UUID.randomUUID().toString();
        requestHeader.getHeaders().addHeader(JsonKey.REQUEST_MESSAGE_ID, reqId);

        return nextFilter
                .apply(requestHeader)
                .thenApply(
                        result -> {
                            long endTime = System.currentTimeMillis();
                            long requestTime = endTime - startTime;

                            log.info(
                                    "{} {} took {}ms and returned {}",
                                    requestHeader.method(),
                                    requestHeader.uri(),
                                    requestTime,
                                    result.status());

                            return result.withHeader("Request-Time", "" + requestTime);
                        });
    }
}