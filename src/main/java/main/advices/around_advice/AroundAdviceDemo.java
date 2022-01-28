package main.advices.around_advice;

import com.example.advices.around_advice.ProfilingInterceptor;
import com.example.advices.around_advice.WorkerBean;
import org.springframework.aop.framework.ProxyFactory;

public class AroundAdviceDemo {
    public static void main(String[] args) {
        WorkerBean proxyWorkerBean = getProxyWorkerBean();
        proxyWorkerBean.doSomeWork(10000000);
    }

    private static WorkerBean getProxyWorkerBean() {
        WorkerBean target = new WorkerBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) proxyFactory.getProxy();
    }
}
