package com.liquidbase.sample;

import com.liquidbase.sample.entity.User;
import com.liquidbase.sample.repository.UserRepository;
import com.liquidbase.sample.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    public void testGetUserById() {
        when(userMapper.findById(1)).thenReturn(new User(1, "Tharindu", "0776288969", "Bentota"));

        User user = userRepository.findById(1);

        assertEquals("Tharindu", user.getName());
        assertEquals("0776288969", user.getMobile());
        assertEquals("Bentota", user.getAddress());
    }

}
