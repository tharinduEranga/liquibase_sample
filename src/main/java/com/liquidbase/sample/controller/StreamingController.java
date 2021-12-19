package com.liquidbase.sample.controller;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/*Returning large JSON arrays from WebFlux endpoint is a challenge.*/
/*We have at least three options defined in this class*/
/*We can also use WebSockets, but it seems like an overkill and complex in this scenario*/

/*source: https://nurkiewicz.com/2021/08/json-streaming-in-webflux.html */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/stream")
public class StreamingController {

    /*Server-sent events pushing individual items as events*/
    @GetMapping(path = "/sse", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Data> sse() {
        log.info("SSE...");
        return source();
    }

    /*Streaming individual events separated by newlines (status quo seems to be application/x-ndjson as Content-Type)*/
    @GetMapping(path = "/ndjson", produces = APPLICATION_NDJSON_VALUE)
    public Flux<Data> ndjson() {
        log.info("ND-JSON...");
        return source();
    }

    /*Returning one large JSON array as individual document*/
    @GetMapping(path = "/json")
    public Flux<Data> json() {
        log.info("JSON...");
        return source();
    }


    private Flux<Data> source() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> new Data(i, Instant.now()));
    }

    @lombok.Data
    @AllArgsConstructor
    static class Data {
        private final long seqNo;
        private final Instant timestamp;
    }
}
