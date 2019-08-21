package stream;

import domain.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Stream4 {

    @Test
    public void Test1(){
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer rs = array.stream()
                .reduce(0, Integer::sum);
        System.out.println(rs);
    }


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
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    @Test
    public void Test3(){
        List<Employee> emps = Arrays.asList(
                new Employee(102, "李四", 59, 6666.66),
                new Employee(101, "张三", 18, 9999.99),
                new Employee(103, "王五", 28, 3333.33),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(105, "田七", 38, 5555.55)
        );
        HashSet<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(set);

        // 总数
        Long count = emps.stream().count();
        System.out.println(count);

        // 平均值
        Double averge = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averge);

        // 总和
        Double salarySum = emps.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(salarySum);

        // 最大值
        Optional<Employee> max = emps.stream().max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(max.get());

        // 最少工资
        OptionalDouble min = emps.stream()
                .mapToDouble(Employee::getSalary).min();
        System.out.println(min.getAsDouble());

        // 分组
        Map<Double, List<Employee>> map1 = emps.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println(map1);

        // 多级分组
        Map<Double, Map<String, List<Employee>>> map2 = emps.stream().collect(Collectors.groupingBy(Employee::getSalary, Collectors.groupingBy(e -> {
            if (e.getAge() < 35) {
                return "青年";
            } else if (e.getAge() < 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));

        System.out.println(map2);


        // 分区
        Map<Boolean, List<Employee>> map3 = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map3);

        // summaring
        DoubleSummaryStatistics dss = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getAverage());

        // 连接
        String s = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "====", "===="));
        System.out.println(s);


    }
}
