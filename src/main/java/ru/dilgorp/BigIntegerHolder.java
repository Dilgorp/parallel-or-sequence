package ru.dilgorp;

import java.math.BigInteger;

public class BigIntegerHolder {
    private static final Object lock = new Object();

    private volatile BigInteger value = BigInteger.ZERO;

    public void setValue(BigInteger value) {
        synchronized (lock) {
            this.value = value.max(this.value);
        }
    }

    public BigInteger getValue() {
        return value;
    }
}
