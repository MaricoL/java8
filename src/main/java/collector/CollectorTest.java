package collector;

import domain.Dish;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorTest {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void Test1() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println); // 466.6666666666667
    }

    @Test
    public void Test2() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println); // 466.6666666666667
    }

    @Test
    public void Test3() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println); // 466.6666666666667
    }

    @Test
    public void Test4() {
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), result -> "The result is :" + result)))
                .ifPresent(System.out::println);
    }


    // 返回不可修改的集合
    @Test
    public void Test5() {
        List<Dish> dishes = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(dishes);
    }

    @Test
    public void Test6() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    @Test
    public void Test7() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void Test8() {
        TreeMap<Dish.Type, Double> map = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(map.getClass());
        System.out.println(map);
    }

    @Test
    public void Test9() {
        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println); // class java.util.concurrent.ConcurrentHashMap
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    @Test
    public void Test10(){
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingDouble(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println); // class java.util.concurrent.ConcurrentHashMap
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
    @Test
    public void Test11(){
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new,Collectors.averagingDouble(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println); // class java.util.concurrent.ConcurrentHashMap
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    @Test
    public void Test12(){
        String collect = menu.stream()
                .collect(Collectors.mapping(Dish::getName, Collectors.joining(",")));
        System.out.println(collect);
    }

    @Test
    public void Test13(){
        Optional<Dish> collect = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        collect.ifPresent(System.out::println);
    }

    @Test
    public void Test14(){
        Optional<Dish> collect = menu.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));
        collect.ifPresent(System.out::println);
    }


}
