package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;
import org.springframework.test.ioc.common.CustomerBeanFactoryPostProcessor;
import org.springframework.test.ioc.common.CustomerBeanPostProcessor;
import org.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.springframwork.beans.factory.xml.XmlBeanDefinitionReader;

import static org.junit.jupiter.api.Assertions.*;

public class BeanFactoryPostProcessorAndBeanPostProcessor {

    @Test
    public void testBeanFactoryPostProcessor(){
        DefaultListableBeanFactory factory = getDefaultListableBeanFactory();
        CustomerBeanFactoryPostProcessor processor = new CustomerBeanFactoryPostProcessor();
        processor.postProcessorBeanFactory(factory);

        Person person = (Person) factory.getBean("person");
        assertEquals("ivy", person.getName());
    }

    @Test
    public void testBeanPostProcessor(){
        DefaultListableBeanFactory factory = getDefaultListableBeanFactory();
        CustomerBeanPostProcessor processor = new CustomerBeanPostProcessor();
        factory.addBeanPostProcessor(processor);

        Car car = (Car) factory.getBean("car");
        assertEquals("lamborghini", car.getBrand());
    }

    private DefaultListableBeanFactory getDefaultListableBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        return factory;
    }


}
