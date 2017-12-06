package jmh.otherallexmples;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author fanwh
 * @version v1.0
 * @decription
 * @create on 2017/12/5 15:05
 */
@State(Scope.Thread)
public class DefaultState {
    double x = Math.PI;

    @Benchmark
    public void measure(){
        x ++;
    }

    public static void main(String[] args) throws RunnerException{
        Options opt = new OptionsBuilder()
                .include(DefaultState.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
