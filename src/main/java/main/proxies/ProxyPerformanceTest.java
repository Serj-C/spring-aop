package main.proxies;

import com.example.proxies.DefaultSimpleBean;
import com.example.proxies.NoOpBeforeAdvice;
import com.example.proxies.SimpleBean;
import com.example.proxies.TestPointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

// To use the CGLIB proxy when proxying an interface,
// you must set the value of the optimize flag in ProxyFactory to true
// by using the setOptimize() method.
public class ProxyPerformanceTest {
    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibProxyTests(advisor, target);
        runCglibProxyFrozenTests(advisor, target);
        runJdkProxyTests(advisor, target);
    }

    private static void runCglibProxyTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB Proxy (Standard) Tests");
        test(proxy);
    }

    private static void runCglibProxyFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setFrozen(true);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB Proxy (Frozen) Tests");
        test(proxy);
    }

    private static void runJdkProxyTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setInterfaces(SimpleBean.class);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running JDK Proxy Tests");
        test(proxy);
    }

    private static void test(SimpleBean proxy) {
        long before = 0;
        long after = 0;

        System.out.println("Testing Advised Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 50000; x++) {
            proxy.adviced();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing Unadvised Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 50000; x++) {
            proxy.unadviced();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing equals() Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 50000; x++) {
            proxy.equals(proxy);
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing hashCode() Method");
        before = System.currentTimeMillis();
        for (int x = 0; x < 50000; x++) {
            proxy.hashCode();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        Advised advised = (Advised) proxy;
        System.out.println("Testing Advised.getProxyTargetClass() Method");
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println(">>>\n");
    }
}
