package com.example.advisors_and_pointcuts.dynamic_method_matcher_pointcut;

public class SampleBean {
    public void foo(int x) {
        System.out.println("Invoked foo() with: " + x);
    }

    public void bar() {
        System.out.println("Invoked bar()");
    }
}
