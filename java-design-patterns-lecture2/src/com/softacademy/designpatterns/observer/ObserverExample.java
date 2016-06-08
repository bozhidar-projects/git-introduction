package com.softacademy.designpatterns.observer;

public class ObserverExample {

    public static void saySomething() {
        System.out.println("Hello from anonymous");
    }

    public static void main(String[] args) {
        Button button = new Button();

        MyButtonObserver myObserver = new MyButtonObserver();
        button.addObserver(myObserver);
        final String someVar = "Heya";
        button.addObserver(new Observer() {

            @Override
            public void onButtonClicked() {
                System.out.println(someVar);
                saySomething();
            }
        });

        button.click();
    }

}
