package Spring_Core.task2.metric;

import Spring_Core.task2.Timed;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MetricProxyPostProcessor implements BeanPostProcessor {
    private final MetricStorage storage;

    @Override
    public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Timed.class)) {
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setTarget(bean);
            proxyFactoryBean.addAdvice((MethodInterceptor) invocation -> {
                Method method = invocation.getMethod();
                long start = System.currentTimeMillis();
                try {
                    return invocation.proceed();
                } finally {
                    long executionTime = System.currentTimeMillis() - start;
                    storage.addMetric(new Metric(method.getName(), LocalDateTime.now(), executionTime));
                    System.out.printf("Class: %s, Method: %s, Executed: %dms%n",
                            beanClass.getName(),
                            method.getName(),
                            executionTime);
                }
            });
            return proxyFactoryBean.getObject();
        } else {
            ProxyFactoryBean proxyFactoryBean = getProxyFactoryBean(bean, beanClass);
            for (Method method : beanClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Timed.class)) {
                    return proxyFactoryBean.getObject();
                }
            }
        }

        return bean;
    }

    private ProxyFactoryBean getProxyFactoryBean(Object bean, Class<?> beanClass) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(bean);

        proxyFactoryBean.addAdvice(new MethodInterceptor() {
            @Override
            public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
                Method method = invocation.getMethod();

                if (method.isAnnotationPresent(Timed.class)) {
                    long start = System.currentTimeMillis();
                    try {
                        return invocation.proceed();
                    } finally {
                        long executionTime = System.currentTimeMillis() - start;
                        storage.addMetric(new Metric(method.getName(), LocalDateTime.now(), executionTime));
                        System.out.printf("Class: %s, Method: %s, Executed: %dms%n",
                                beanClass.getName(),
                                method.getName(),
                                executionTime);
                    }
                } else {
                    return invocation.proceed();
                }
            }
        });
        return proxyFactoryBean;
    }

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
