package com.liquidbase.sample.repository;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final UserMapper userMapper;

    public Integer add(User user) {
        return userMapper.save(user);
    }

    public void update(User user) {
        userMapper.save(user);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public List<User> findByName(String name) {
        return userMapper.findByName(name);
    }

    public void delete(int id) {
        userMapper.delete(id);
    }
}
