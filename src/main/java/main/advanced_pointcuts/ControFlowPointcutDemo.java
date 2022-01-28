package main.advanced_pointcuts;

import com.example.common.SimpleBeforeAdvice;
import com.example.advanced_pointcuts.control_flow_pointcut.TestBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControFlowPointcutDemo {
    public static void main(String[] args) {
        ControFlowPointcutDemo controFlowPointcutDemo = new ControFlowPointcutDemo();
        controFlowPointcutDemo.run();
    }

    public void run() {
        TestBean target = new TestBean();
        Pointcut pointcut = new ControlFlowPointcut(ControFlowPointcutDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        TestBean proxy = (TestBean) proxyFactory.getProxy();
        System.out.println("\tTrying normal invoke");
        proxy.foo();
        System.out.println("\n\tTrying under ControlFlowPointcutDemo.test()");
        test(proxy);
    }

    private void test(TestBean bean) {
        bean.foo();
    }
}
