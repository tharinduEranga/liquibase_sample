package com.liquidbase.sample.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class BaseDao {

    private final SqlSessionFactory sqlSessionFactory;

    public <T> Mono<T> apply(Function<SqlSession, T> function) {
        return Mono.just(applyFunction(function));
    }

    public <T> Flux<T> applyList(Function<SqlSession, List<T>> function) {
        return monoToFlux(Mono.just(applyListFunction(function)));
    }


    private <T> T applyFunction(Function<SqlSession, T> function) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return function.apply(session);
        }
    }

    private <T> List<T> applyListFunction(Function<SqlSession, List<T>> function) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return function.apply(session);
        }
    }

    private <T> Flux<T> monoToFlux(Mono<List<T>> monoList) {
        return monoList
                .flatMapIterable(list -> list)
                .log();
    }
}