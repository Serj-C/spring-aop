package com.example.advisors_and_pointcuts.static_method_matcher_pointcut;

import com.example.common.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I shot the sheriff,\n"
                + "But I did not shot the deputy");
    }
}
