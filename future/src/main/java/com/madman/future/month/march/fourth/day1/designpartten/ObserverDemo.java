package com.madman.future.month.march.fourth.day1.designpartten;


import java.util.ArrayList;
import java.util.List;

class ObserverDemo {

    static List<Observer> observers = new ArrayList<>();
    {
        observers.add(new A());
        observers.add(new B());
    }

    void  pubJob(){
        for (Observer observer : observers) {
            observer.some();
        }
    }
    public static void main(String[] args) {
        ObserverDemo demo = new ObserverDemo();
        demo.pubJob();

    }




    interface Observer{
        void some();
    }

    class A implements Observer{

        @Override
        public void some() {
            System.out.println("A");
        }
    }

    class B implements Observer{

        @Override
        public void some() {
            System.out.println("B");
        }
    }

}

