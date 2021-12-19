package com.liquidbase.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/reactor-sample")
public class SampleReactorController {

    private final List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @GetMapping(path = "/words")
    public Flux<String> getWords() {
        log.info("Get words Thread: {}", Thread.currentThread().getId());

        return Flux.fromIterable(words);
    }

    @GetMapping(path = "/missing-letters")
    public Flux<String> prependIndex() {
        log.info("Prepend Index Thread: {}", Thread.currentThread().getId());

        return Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE), // second publisher to zip with this source
                        (string, count) -> String.format("%-40s%s%n", count, string)); // combine format of the two
    }
}
