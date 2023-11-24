package com.example.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FactorialServiceTest {

    @InjectMocks
    private FactorialService factorialService;

    @Test
    public void testFactorial() {
        int number = 5;
        long expected = 120;
        long actual = factorialService.factorial(number);
        assertEquals(expected, actual);
    }
}
