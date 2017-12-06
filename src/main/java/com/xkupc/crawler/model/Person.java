package com.xkupc.crawler.model;

/**
 * @author xk
 * @createTime 2017/12/1 0001 上午 9:47
 * @description
 */
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int compareWithNameAndAge(Person p1, Person p2) {
        if (p1.getName().equals(p2.getName())) {
            return p1.getAge() - p2.getAge();
        }
        return p1.getName().compareTo(p2.getName());
    }
}
