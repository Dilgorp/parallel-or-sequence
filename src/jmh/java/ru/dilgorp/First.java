package ru.dilgorp;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(value = 1, warmups = 1)
@BenchmarkMode(Mode.AverageTime)
public class First {

    private Calculator calculator;
    private static final long SIZE = 7_000_000L;

    @Setup
    public void setUp() {
        calculator = new Calculator();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void increase(Blackhole bh) {
        bh.consume(calculator.increase(BigInteger.ZERO));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void max(Blackhole bh) {
        bh.consume(BigInteger.valueOf(1_000_000_000_00L).max(BigInteger.valueOf(999_999_999_99L)));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void streamSequence(Blackhole bh) {
        bh.consume(calculator.calculateWithSequenceStream(SIZE));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void streamParallel(Blackhole bh) {
        bh.consume(calculator.calculateWithParallelStream(SIZE));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void loop(Blackhole bh) {
        bh.consume(calculator.calculateWithSingleLoop(SIZE));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void loopParallel(Blackhole bh) throws InterruptedException {
        bh.consume(calculator.calculateWithParallelLoop(SIZE));
    }
}
