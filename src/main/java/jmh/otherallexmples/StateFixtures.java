package jmh.otherallexmples;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author fanwh
 * @version v1.0
 * @decription
 * @create on 2017/12/6 16:34
 */
@State(Scope.Thread)
public class StateFixtures {

    double x;

    @Setup
    public void prepare() {
        x = Math.PI;
    }

    @TearDown
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    @Benchmark
    public void measureRight() {
        x++;
    }

    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StateFixtures.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}
