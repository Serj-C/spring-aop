package main.advices.before_advice;

import com.example.advices.before_advice.SecureBean;
import com.example.advices.before_advice.SecurityAdvice;
import com.example.advices.before_advice.SecurityManager;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdviceSecuredMethodAccess {
    public static void main(String[] args) {
        SecurityManager securityManager = new SecurityManager();

        SecureBean bean = getProxySecureBean();

        securityManager.login("John", "pwd");
        bean.writeSecureMessage();
        securityManager.logout();

        try {
            securityManager.login("invalid user", "pwd");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            securityManager.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    private static SecureBean getProxySecureBean() {
        SecureBean target = new SecureBean();

        SecurityAdvice advice = new SecurityAdvice();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);

        return (SecureBean) proxyFactory.getProxy();
    }
}
