package main.advisors_and_pointcuts;

import com.example.common.Guitarist2;
import com.example.common.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class JdkRegexpMethodPointcutDemo {
    public static void main(String[] args) {
        Guitarist2 target = new Guitarist2();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing.*");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        Guitarist2 proxy = (Guitarist2) proxyFactory.getProxy();

        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
