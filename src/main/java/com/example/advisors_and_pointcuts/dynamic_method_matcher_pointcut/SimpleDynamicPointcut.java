package com.example.advisors_and_pointcuts.dynamic_method_matcher_pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    // Static check gets cached and the dynamic check doesn't have to happen every time bar() method is invoked.
    // Doing static check for methods and dynamic check for arguments is a good practice and increases performance.
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for " + method.getName());
        return ("foo".equals(method.getName()));
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Dynamic check for " + method.getName());

        int x = (int) args[0];

        return (x != 100);
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> (clazz == SampleBean.class);
    }
}
