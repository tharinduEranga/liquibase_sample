package com.liquidbase.sample.repository.dao;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.BaseDao;
import com.liquidbase.sample.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserRepository {

    private static final String MAPPER_CLASS = "com.liquidbase.sample.repository.mapper.UserMapper.";

    private final UserMapper userMapper;
    private final BaseDao baseDao;

    public Integer add(User user) {
        return userMapper.save(user);
    }

    public void update(User user) {
        userMapper.save(user);
    }

    public Mono<User> findById(int id) {
        return baseDao.apply((session) ->
                session.selectOne(MAPPER_CLASS + "findById", id));
    }

    public Flux<User> findByName(String name) {
        return baseDao.applyList((session) ->
                session.selectList(MAPPER_CLASS + "findByName", name));
    }

    public void delete(int id) {
        userMapper.delete(id);
    }

}
