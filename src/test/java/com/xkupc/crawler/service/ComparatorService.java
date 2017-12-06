package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.Person;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xk
 * @createTime 2017/12/1 0001 上午 9:53
 * @description
 */
public class ComparatorService extends BaseTestService {

    private List<Person> personList;

    @Before
    public void initPersonList() {
        personList = Lists.newArrayList(
                new Person("f", 20),
                new Person("c", 21),
                new Person("g", 18),
                new Person("e", 18),
                new Person("h", 25),
                new Person("r", 12),
                new Person("s", 17),
                new Person("x", 18),
                new Person("d", 10),
                new Person("t", 9));
    }

    @After
    public void printPersonList() {
        personList.stream().forEach(person -> System.err.println(person.getName()));
    }

    //比较
    @Test
    public void compareWithInnerClass() {
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int i = o1.getName().compareTo(o2.getName());
                return i;
            }
        });
    }

    @Test
    public void compareWithLamda() {
        Collections.sort(personList, Comparator.comparing(Person::getName));
    }

    @Test
    public void compareWithLamdaReversed() {
        Collections.sort(personList, Comparator.comparing(Person::getName).reversed());
    }

    @Test
    public void compareWithLamdaMore() {
        Collections.sort(personList, Comparator.comparing(Person::getName).thenComparing(Person::getAge));
    }

    @Test
    public void compareWithNewSort() {
        personList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
    }

    @Test
    public void compareWithLamdaMethod() {
        personList.sort(Person::compareWithNameAndAge);
    }

    @Test
    public void test(){
        int off = 0;
        function(-off);
    }
    private void function(int off){
        System.err.println(off);
    }
}
