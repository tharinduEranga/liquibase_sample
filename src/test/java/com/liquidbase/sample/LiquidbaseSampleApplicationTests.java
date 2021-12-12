package com.liquidbase.sample;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.UserRepository;
import com.liquidbase.sample.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
class LiquidbaseSampleApplicationTests {

    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddUser() {
        User user = new User(1, "Tharindu", "0776288969", "Bentota");
        userRepository.add(user);
        verify(userMapper, times(1)).save(user);
    }

}
