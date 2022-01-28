package main.advisors_and_pointcuts;

import com.example.advisors_and_pointcuts.annotation_matching_pointcut.AdviceRequired;
import com.example.advisors_and_pointcuts.annotation_matching_pointcut.Guitarist;
import com.example.common.Guitar;
import com.example.common.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationMatchingPointcutDemo {
    public static void main(String[] args) {
        Guitarist target = new Guitarist();

        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}
