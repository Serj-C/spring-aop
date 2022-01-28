package com.example.advisors_and_pointcuts.annotation_matching_pointcut;

import com.example.common.Guitar;
import com.example.common.Singer;

public class Guitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Dream of ways to throw it all away\n");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }
}
