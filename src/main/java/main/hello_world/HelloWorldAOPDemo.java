package main.hello_world;

import com.example.hello_world.Agent;
import com.example.hello_world.AgentInterceptor;
import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldAOPDemo {
    public static void main(String[] args) {
        Agent target = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new AgentInterceptor());

        Agent proxy = (Agent) proxyFactory.getProxy();

        target.speak();
        System.out.println();
        proxy.speak();
    }
}
