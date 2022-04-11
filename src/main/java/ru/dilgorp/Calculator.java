package ru.dilgorp;

import java.math.BigInteger;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Calculator {

    BigInteger increase(BigInteger value) {
        return value.add(BigInteger.ONE);
    }

    BigInteger calculateWithSequenceStream(long size) {
        return stream().limit(size).reduce(BigInteger.ZERO, BigInteger::max);
    }

    BigInteger calculateWithParallelStream(long size) {
        return stream().parallel().limit(size).reduce(BigInteger.ZERO, BigInteger::max);
    }

    private Stream<BigInteger> stream() {
        var iterator = new BigIntegerGrowingIterator();
        var spliterator = Spliterators.spliteratorUnknownSize(iterator,
                Spliterator.NONNULL | Spliterator.IMMUTABLE);
        return StreamSupport.stream(spliterator, false);
    }

    BigInteger calculateWithSingleLoop(long size){
        BigInteger result = BigInteger.ZERO;

        for (long i = 0; i < size; i++) {
            result = result.add(BigInteger.ONE);
        }

        return  result;
    }

    BigInteger calculateWithParallelLoop(final long size) throws InterruptedException {
        final BigIntegerHolder holder = new BigIntegerHolder();
        final long t1Start = 0;
        final long t1End = size / 2;

        Thread t1 = new Thread(() -> {
            BigInteger result = BigInteger.valueOf(t1Start);
            for(long i = t1Start; i < t1End; i++){
                result = result.add(BigInteger.ONE);
            }
            holder.setValue(result);
        });

        Thread t2 = new Thread(() -> {
            BigInteger result = BigInteger.valueOf(t1End);
            for(long i = t1End; i < size; i++){
                result = result.add(BigInteger.ONE);
            }
            holder.setValue(result);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return holder.getValue();
    }
}
