package com.liquidbase.sample.controller;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    private Mono<User> getEmployeeById(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

}