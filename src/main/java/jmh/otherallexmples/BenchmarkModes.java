package jmh.otherallexmples;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author fanwh
 * @version v1.0
 * @decription
 * @create on 2017/12/5 13:46
 */
public class BenchmarkModes {

    /**
     *Mode.Throughput，用于衡量在某个时间限制内
     * 不断循环调用benchmark 方法的原始吞吐量，
     * 同时统计方法的执行次数
     *
     * 这里采用指定的时间单位注解annotation进行测量，
     * 当然也可以采用默认值
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * Mode.AverageTime 衡量平均执行时间，与Mode.Throughput执行方式类似。
     * 也被人称为Mode.Throughput的倒数，的确如此
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAvgTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    /**Mode.SampleTime ,取样执行时间，在这种模式下，我们仍然在某个时间范围内执行方法
     * 但是，只是衡量部分benchmark方法调用锁花费的时间
     * 这种模式允许我们推断分布、分位点等。
     * jmh还尝试自动调节取样频率：如果方法足够长的话，你会获取所有的样本
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    /*
     * Mode.SingleShotTime 用于衡量单个方法的执行时间。正如javadoc建议
     * 我们只执行单个方法的benchmark调用。在这种模式下，迭代时间没有任何意义。
     *这种模式在你明确不需要继续执行benchmark方法的情况下非常有用
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    /**
     *同时开启多重模式的benchmark
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkModes.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
