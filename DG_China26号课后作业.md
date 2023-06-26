# DG_China26号课后作业



###### 熟悉关于java8 stream 的相关API



##### 1.filter(Function) 使用给定Function过滤流中元素，返回满足条件元素流

~~~java
list.stream().filter(e->e.getSalary()>8000).forEach(t->{
            System.out.println("工资大于8000的狠人"+t);
~~~

##### 2.distinct() 集合元素去重

~~~java
List<Integer> list0 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinctNumbers = list0.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctNumbers);
~~~

##### 3.limit(long maxSize) 限制流的大小，返回包含最多指定数量元素的新流

~~~java
list.stream().limit(3).forEach(t-> System.out.println("输出集合"+t));
~~~

##### 4.map(Object::Function) 将流中的元素通过给定的映射函数（`Function`）转换成另一种类型，返回转换后的元素组成的新流

~~~java
Stream<String> nameStream = list.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(t-> System.out.println("获取员工姓名大于3的员工->>>"+t));
~~~

##### 5.faltMap(Function) Function指定分割条件，将流中的每个元素映射为一个流，然后将这些流扁平化为单个流

~~~java
List<String> list1 = Arrays.asList("a,b,c", "1,2,3");
        Stream<String> s3 = list1.stream().flatMap(s -> {//将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println);
~~~

##### 6.sorted((object1,object2)->Function)自动两两比较流中元素Funcution制定比较规则

~~~java
list.stream().sorted((e1,e2)->{
            int age = Integer.compare(e1.getAge(),e2.getAge());
            if(age != 0){
                return age;
            }else {
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
~~~

##### 7.anyMatch(object->Function) 根据Function判断流中是否至少有一个元素满足给定的条件

~~~java
boolean anyMatch = list.stream().anyMatch(employee -> employee.getSalary() > 8000);
        System.out.println(anyMatch);//false说明没有一个salary超过8000
~~~

##### 8.noneMatch(object->Function) 根据Function判断流中是否没有元素满足给定的条件

```
boolean noneMatch = list.stream().noneMatch(employee -> employee.getName().startsWith("y"));
System.out.println(noneMatch);//false 说明有
```

##### 9.allMatch(object->Function) 判断流中是否所有元素都满足给定的条件

~~~java
boolean allMatch = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);//false说明没有员工不到18岁
~~~

##### 10.findAny() 返回当前流中的任意元素(如果存在)

~~~java
Optional<Employee> any = list.parallelStream().findAny();
        System.out.println(any);
~~~

##### 11.findFirst() 返回流中的第一个元素（如果存在）

~~~java 
Optional<Employee> first = list.stream().findFirst();
        System.out.println(first);
~~~

##### 12.collect将流中的元素收集到一个容器中，封装进List或者Set。

~~~java
List<Employee> collect = list.stream().filter(employee -> employee.getSalary() > 8000).collect(Collectors.toList());
        //List<Employee> collect = list.stream().filter(employee -> employee.getSalary() > 8000).collect(Collectors.toSet());
        System.out.println(collect);
~~~

##### 13.reduce(identity, BinaryOperator<T> accumulator) 使用给定的初始值（`identity`）和二元操作（`BinaryOperator`）对流中的元素进行归约操作，返回归约结果。

~~~java
List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list2.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
~~~

##### 14.count：返回流中元素的总个数

~~~java
long count = list.stream().count();
        System.out.println(count);
~~~



