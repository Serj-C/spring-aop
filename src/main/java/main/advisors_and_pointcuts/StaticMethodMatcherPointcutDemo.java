package main.advisors_and_pointcuts;

import com.example.common.Singer;
import com.example.advisors_and_pointcuts.static_method_matcher_pointcut.GoodGuitarist;
import com.example.advisors_and_pointcuts.static_method_matcher_pointcut.GreatGuitarist;
import com.example.common.SimpleAdvice;
import com.example.advisors_and_pointcuts.static_method_matcher_pointcut.SimpleStaticPointcut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticMethodMatcherPointcutDemo {
    public static void main(String[] args) {
        GoodGuitarist johnMayer = new GoodGuitarist();
        GreatGuitarist ericClapton = new GreatGuitarist();

        Singer proxyJohnMayer;
        Singer proxyEricClapton;

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);
        proxyJohnMayer = (Singer) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(ericClapton);
        proxyFactory.addAdvisor(advisor);
        proxyEricClapton = (Singer) proxyFactory.getProxy();

        proxyJohnMayer.sing();
        proxyEricClapton.sing();
    }
}
