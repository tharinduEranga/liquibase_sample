package com.liquidbase.sample.repository.mapper;

import com.liquidbase.sample.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Mapper
public interface UserMapper {
    int save(@Param("user") User user);

    Mono<User> findById(int id);

    Flux<User> findByName(@Param("name") String name);

    void delete(@Param("id") int id);
}
