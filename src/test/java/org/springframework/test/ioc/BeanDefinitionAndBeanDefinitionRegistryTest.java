package org.springframework.test.ioc;

import org.springframework.test.ioc.service.HelloService;
import org.junit.Test;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.support.DefaultListableBeanFactory;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void TestBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        factory.registryBeanDefinition("hello", beanDefinition);

        HelloService helloService = (HelloService) factory.getBean("hello");
        assert helloService != null;
    }
}
