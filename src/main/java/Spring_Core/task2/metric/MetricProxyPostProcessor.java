package Spring_Core.task2.metric;

import Spring_Core.task2.Timed;
import Spring_Core.task2.metric.Metric;
import Spring_Core.task2.metric.MetricStorage;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
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
        if (!beanClass.isAnnotationPresent(Timed.class)) {
            return bean;
        }

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
                System.out.println("Method " + method.getName() + " executed in " + executionTime + "ms");
            }
        });
        return proxyFactoryBean.getObject();
    }

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
