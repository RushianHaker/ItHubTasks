
package org.sample;


import com.company.Task1Impl;
import ru.java.*;
import org.openjdk.jmh.annotations.Benchmark;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MyBenchmark {

    @Benchmark
    public void testMethod1() {
        Main main = new Main();

        main.version1();

    }

    @Benchmark
    public void testMethod2() {
        Main main = new Main();

        main.version2();

    }
}
