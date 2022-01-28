package main.advices.throws_advice;

import com.example.advices.throws_advice.ErrorBean;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

// ThrowsAdvice is a marker interface
// Spring looks for public afterThrowing methods in this class
public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(errorBean);
        proxyFactory.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) proxyFactory.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception e) {
            // Exception is ignored, handled in
            // afterThrowing(Exception e) method.
        }

        try {
            proxy.otherErrorProneMethod();
        } catch (Exception e) {
            // Exception is ignored, handled in
            // afterThrowing(Method method, Object args, Object target, IllegalArgumentException e) method.
        }
    }

    public void afterThrowing(Exception e) throws Throwable {
        System.out.println("***");
        System.out.println("Generic Exception Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("***\n");
    }

    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException e)
            throws Throwable {
        System.out.println("***");
        System.out.println("IllegalArgumentException Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("***\n");
    }
}
