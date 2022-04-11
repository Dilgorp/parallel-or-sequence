package ru.dilgorp;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculatorTest {

    private static final long SIZE = 7_000_000L;

    private static final BigInteger expected = BigInteger.valueOf(SIZE);

    @Test
    void calculateWithSequenceStreamIsCorrect() {
        assertEquals(expected, new Calculator().calculateWithSequenceStream(SIZE));
    }

    @Test
    void calculateWithParallelStreamIsCorrect() {
        BigInteger actual = new Calculator().calculateWithParallelStream(SIZE);
        assertEquals(expected, actual, actual.toString());
    }

    @Test
    void calculateWithSingleLoopIsCorrect(){
        BigInteger actual = new Calculator().calculateWithSingleLoop(SIZE);
        assertEquals(expected, actual);
    }

    @Test
    void calculateParallelLoopIsCorrect() throws InterruptedException {
        BigInteger actual = new Calculator().calculateWithParallelLoop(SIZE);
        assertEquals(expected, actual);
    }
}