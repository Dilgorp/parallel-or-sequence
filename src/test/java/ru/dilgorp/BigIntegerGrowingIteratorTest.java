package ru.dilgorp;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigIntegerGrowingIteratorTest {

    private final BigIntegerGrowingIterator iterator = new BigIntegerGrowingIterator();

    @Test
    void iterateIsCorrect(){
        var i = 0;
        var result = BigInteger.ZERO;
        for (BigIntegerGrowingIterator it = iterator; it.hasNext(); ) {
            BigInteger value = it.next();
            i++;

            if(i == 10){
                result = value;
                break;
            }
        }

        assertEquals(result, BigInteger.TEN);
    }
}