package ru.dilgorp;

import java.math.BigInteger;
import java.util.Iterator;

public class BigIntegerGrowingIterator implements Iterator<BigInteger> {
    private BigInteger current = BigInteger.ZERO;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        return current = current.add(BigInteger.ONE);
    }
}
