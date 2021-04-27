package delivery;

import java.io.BufferedReader;

public abstract class Person {
    private String name;
    private int age;

    abstract String getType();

    Person() {
        this.name = "undefined";
        this.age = 0;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setAge(int x) {
        this.age = x;
    }

    public abstract void readFromFile(BufferedReader br);
}
