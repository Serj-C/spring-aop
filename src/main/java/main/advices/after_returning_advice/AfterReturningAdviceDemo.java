package main.advices.after_returning_advice;

import com.example.advices.after_returning_advice.KeyGenerator;
import com.example.advices.after_returning_advice.WeakKeyCheckAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class AfterReturningAdviceDemo {
    public static void main(String[] args) {
        KeyGenerator keyGenerator = getProxyKeyGenerator();

        for (int x = 0; x < 10; x++) {
            try {
                long key = keyGenerator.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException e) {
                System.out.println("Weak Key Generated!: " + e.getMessage());
            }
        }
    }

    private static KeyGenerator getProxyKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new WeakKeyCheckAdvice());

        return (KeyGenerator) proxyFactory.getProxy();
    }
}
