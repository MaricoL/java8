package stream;

import java.util.Arrays;
import java.util.List;

public class Stream3 {
    public static void main(String[] args) {

        List<String> array = Arrays.asList("aaa", "bbb", "ccc");

         array.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
