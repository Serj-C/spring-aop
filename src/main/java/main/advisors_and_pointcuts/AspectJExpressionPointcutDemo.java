package main.advisors_and_pointcuts;

import com.example.common.Guitarist2;
import com.example.common.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectJExpressionPointcutDemo {
    public static void main(String[] args) {
        Guitarist2 target = new Guitarist2();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*(..))");

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
