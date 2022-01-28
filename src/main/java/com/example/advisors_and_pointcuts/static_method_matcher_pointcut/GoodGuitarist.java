package com.example.advisors_and_pointcuts.static_method_matcher_pointcut;

import com.example.common.Singer;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says I can't be free\n"
                + "From all of the things that I used to be");
    }
}
