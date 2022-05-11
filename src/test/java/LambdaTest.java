import com.uin.pojo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    @Test
    public void test01() {
        //比较两个值的大小
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        // 改称lambda表达式的写法
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
    }

    List<Person> personList = Arrays.asList(
            new Person("we", 18, 188, 3800),
            new Person("wwe", 12, 188, 3500),
            new Person("wee", 18, 168, 3800),
            new Person("wre", 17, 188, 35000),
            new Person("wye", 18, 178, 3900),
            new Person("wqe", 18, 198, 3950)
    );

    //需求：学员的年龄大于25的
    public List<Person> filterPerson(List<Person> personList) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        for (Person person : personList) {
            //判断
            if (person.getAge() > 25) {
                personArrayList.add(person);
            }
        }
        return personArrayList;
    }

    //需求：学员的年龄大于25的 使用stream和lambda
    public List<Person> filterPerson_lambda_stream(List<Person> personList) {
        List<Person> collect = personList.stream()
                .distinct()
                .filter(item -> item.getAge() > 25)
                .collect(Collectors.toList());
        return collect;
    }

    //需求：学员的工资大于25000的
    public List<Person> filterSalary(List<Person> personList) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (person.getSalary() > 25000) {
                result.add(person);
            }
        }
        return result;
    }

    //需求：学员的工资大于25000的 使用stream和lambda
    public List<Person> filterSalary_lambda_stream(List<Person> personList) {
        List<Person> collect = personList.stream()
                //去重
                .distinct()
                //过滤 类似与筛选出工资大于25000
                .filter(item -> item.getSalary() > 25000)
                //收集
                .collect(Collectors.toList());

        return collect;
    }


}
