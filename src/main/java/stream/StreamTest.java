package stream;

import domain.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    /*
    * 1. 给定[1,2,3,4,5]，返回[1,4,9,16,25]
    */
    @Test
    public void Test1(){
        Integer[] nums = {1, 2, 3, 4, 5};
        Arrays.stream(nums).map(num -> num * num).forEach(System.out::println);
    }

    /*
    * 2. 用map和reduce获取流中Employee的个数
    */
    @Test
    public void Test2(){
        List<Employee> emps = Arrays.asList(
                new Employee(102, "李四", 59, 6666.66),
                new Employee(101, "张三", 18, 9999.99),
                new Employee(103, "王五", 28, 3333.33),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(105, "田七", 38, 5555.55)
        );

        System.out.println(emps.stream().map(e -> 1).reduce(0,Integer::sum));
    }


}
