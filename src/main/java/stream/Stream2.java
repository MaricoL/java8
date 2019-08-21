package stream;

import java.util.stream.Stream;

public class Stream2 {

    public static void main(String[] args) {
        // 创建无限流的两种方式
        // 1.
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 2);
        stream1.forEach(System.out::println);

        // 2.
        Stream<Double> stream2 = Stream.generate(Math::random);
        stream2.forEach(System.out::println);
    }
}
