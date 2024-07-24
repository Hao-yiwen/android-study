package com.yiwen.java_view_other.moshiTest;

public class Cat {
    private String name;
    private int age;
    private boolean isMale;

    public Cat(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale;
    }
}
