package com.liquidbase.sample.repository.mapper;

import com.liquidbase.sample.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int save(@Param("user") User user);

    User findById(int id);

    List<User> findByName(@Param("name") String name);

    int delete(@Param("id") int id);
}
