package com.liquidbase.sample.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class BaseDao {

    private final SqlSessionFactory sqlSessionFactory;

    public <R> Mono<R> apply(Function<SqlSession, R> function) {
        return Mono.just(applyFunction(function));
    }

    private <R> R applyFunction(Function<SqlSession, R> function) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return function.apply(session);
        }
    }
}