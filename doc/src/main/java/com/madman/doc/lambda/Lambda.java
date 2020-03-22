package com.madman.doc.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda {

    interface Printer {
        void print(String val);
    }

    public void print(String val, Printer printer) {
        printer.print(val);
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        String msg = "msg";
//        Printer printer = new Printer() {
//            @Override
//            public void print(String val) {
//                System.out.println(val);
//            }
//        };

//        Printer printer = (String val) -> {
//            System.out.println(val);
//        };

//        Printer printer = val -> System.out.println(val);

        Printer printer = System.out::println;

        lambda.print(msg, printer);
    }
}


class Stream {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("kobe");
        arr.add("james");
        arr.add("curry");
        arr.add("cxk");

//        for (String name : arr) {
//            if (name.startsWith("c")) {
//                String upName = name.toUpperCase();
//            }
//        }

        List<String> c = arr.stream()
                .filter(name -> name.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(c);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;

    public static java.util.function.Predicate<Employee> ageGreaterThan40 = employee -> employee.getAge() > 40;
    public static java.util.function.Predicate<Employee> f = employee -> "F".equals(employee.getGender());
}

/**
 * Predicate 谓词，表示 是什么  sql中的where后面的条件
 */
class Predicate {


    public static void main(String[] args) {
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethvo");
        Employee e2 = new Employee(2, 13, "F", "Martin", "Hent");
        Employee e3 = new Employee(3, 26, "M", "Jon", "Lo");
        Employee e4 = new Employee(4, 29, "F", "Ricky", "Mar");
        Employee e5 = new Employee(5, 35, "M", "Cristine", "Maria");
        Employee e6 = new Employee(6, 58, "F", "David", "Feezor");
        Employee e7 = new Employee(7, 26, "M", "Melisea", "Roy");
        Employee e8 = new Employee(8, 28, "F", "Alex", "Singh");
        Employee e9 = new Employee(9, 23, "M", "Naveen", "Jaim");


        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9);
//        List<Employee> collect = employees.stream()
//                .filter(employee -> employee.getAge() > 40 && "F".equals(employee.getGender()))
//                .collect(Collectors.toList());
        List<Employee> collect = employees.stream()
                .filter(Employee.ageGreaterThan40
                        .or(Employee.f)
                .negate())
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}

/**
 * 生产，只有出参没有入参
 */
class Supplier {


    public static void main(String[] args) {
        java.util.function.Supplier<Employee> employeeSupplier = Employee::new;
        Employee employee = employeeSupplier.get();
        System.out.println(employee);
    }
}

/**
 * 消费 ， 只有入参没有出参
 */
class Consumer {
    public static void main(String[] args) {
        java.util.function.Consumer<Employee> employeeConsumer = employee -> {
            System.out.println("操做从外输入的对象，没有返回值" + employee);
        };

        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethvo");
        Employee e2 = new Employee(2, 13, "F", "Martin", "Hent");
        Employee e3 = new Employee(3, 26, "M", "Jon", "Lo");
        Employee e4 = new Employee(4, 29, "F", "Ricky", "Mar");
        Employee e5 = new Employee(5, 35, "M", "Cristine", "Maria");
        Employee e6 = new Employee(6, 58, "F", "David", "Feezor");
        Employee e7 = new Employee(7, 26, "M", "Melisea", "Roy");
        Employee e8 = new Employee(8, 28, "F", "Alex", "Singh");
        Employee e9 = new Employee(9, 23, "M", "Naveen", "Jaim");

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9);
        employees.forEach(employeeConsumer);
    }
}

/**
 * 转换 将第一个类型转为第二个类型 Function<T, R>
 */
class Function {
    public static void main(String[] args) {
        java.util.function.Function<Employee, String> employeeStringFunction
                = employee -> {
            return employee.toString();
        };

        Employee e9 = new Employee(9, 23, "M", "Naveen", "Jaim");
        System.out.println(e9);
        String apply = employeeStringFunction.apply(e9);
        System.out.println(apply);
    }
}



