package com.liquidbase.sample.controller;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("")
    private Flux<User> getUsersByName(@RequestParam(name = "name") String name) {
        log.info("Thread: {}", Thread.currentThread().getId());
        return userRepository.findByName(name);
    }

    @GetMapping("/{id}")
    private Mono<User> getUserById(@PathVariable Integer id) {
        log.info("Thread: {}", Thread.currentThread().getId());
        return userRepository.findById(id);
    }

}