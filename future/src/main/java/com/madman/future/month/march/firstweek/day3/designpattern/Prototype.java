package com.madman.future.month.march.firstweek.day3.designpattern;

/**
 * 原型模式
 */
public class Prototype {

    public static void main(String[] args) {
        Sun sun = new Sun();
        sun.setAge("19");
        sun.setName("sjf");
        Sun clone = (Sun) sun.clone();
        clone.setAge("22");
        System.out.println(sun);
        System.out.println(clone);
    }
}

class Sun implements Cloneable{
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Object clone(){
        Sun sun = null;
        try {
            sun = (Sun) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sun;
    }
}