import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Employee> list=new ArrayList<>();
        list.add(new Employee(1,"tom",20,8500));
        list.add(new Employee(1,"tom",20,8500));
        list.add(new Employee(2,"jam",20,800));
        list.add(new Employee(3,"yazchen",20,500));
        list.add(new Employee(4,"chew",20,8300));


        System.out.println("===========================1.filter 筛选==================================");
        list.stream().filter(e->e.getSalary()>8000).forEach(t->{
            System.out.println("工资大于8000的狠人"+t);
        });

        System.out.println("==========================2.distinct 集合去重===============================");
        List<Integer> list0 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinctNumbers = list0.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctNumbers);

        System.out.println("=======================3.limit 限制输出集合元素数量=============================");
        list.stream().limit(3).forEach(t-> System.out.println("输出集合"+t));

        System.out.println("=========================4.map 类型转换======================================");
        Stream<String> nameStream = list.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(t-> System.out.println("获取员工姓名大于3的员工->>>"+t));

        System.out.println("==========================5.faltMap 扁平流或者扩展流===========================");
        List<String> list1 = Arrays.asList("a,b,c", "1,2,3");
        Stream<String> s3 = list1.stream().flatMap(s -> {//将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println);

        System.out.println("=============================6.sorted 排序==================================");
        list.stream().sorted((e1,e2)->{
            int age = Integer.compare(e1.getAge(),e2.getAge());
            if(age != 0){
                return age;
            }else {
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);


        System.out.println("=====================7.anyMatch 检查是否至少匹配一个元素===========================");
        boolean anyMatch = list.stream().anyMatch(employee -> employee.getSalary() > 8000);
        System.out.println(anyMatch);//false说明没有一个salary超过8000

        System.out.println("=======================8.noneMatch：检查是否没有匹配的元素=========================");
        boolean noneMatch = list.stream().noneMatch(employee -> employee.getName().startsWith("y"));
        System.out.println(noneMatch);//false 说明有

        System.out.println("========================9.allMatch：检查是否匹配所有元素==========================");
        boolean allMatch = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);//false说明没有员工不到18岁

        System.out.println("=====================10.findAny：返回当前流中的任意元素============================");
        Optional<Employee> any = list.parallelStream().findAny();
        System.out.println(any);

        System.out.println("=========================11.findFirst：返回第一个元素=============================");
        Optional<Employee> first = list.stream().findFirst();
        System.out.println(first);

        System.out.println("=========================12.collect 收集========================================");
        List<Employee> collect = list.stream().filter(employee -> employee.getSalary() > 8000).collect(Collectors.toList());
        //List<Employee> collect = list.stream().filter(employee -> employee.getSalary() > 8000).collect(Collectors.toSet());
        System.out.println(collect);

        System.out.println("================13.reduce：可以将流中的元素反复结合起来，得到一个值=====================");
        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list2.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        System.out.println("======================14.count：返回流中元素的总个数================================");
        long count = list.stream().count();
        System.out.println(count);
    }

}
