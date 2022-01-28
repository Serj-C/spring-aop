package com.example.advices.around_advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());

        Object returnValue = invocation.proceed();

        stopWatch.stop();

        dumpInfo(invocation, stopWatch.getTotalTimeMillis());

        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();

        System.out.println("Executed method: " + method.getName());
        System.out.println("Object of type: " + target.getClass().getName());
        System.out.println("With arguments:");
        for (int x = 0; x < args.length; x++) {
            System.out.print("\t> " + args[x]);
        }
        System.out.println();

        System.out.println("Took: " + ms + " ms");
    }
}
