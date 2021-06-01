package org.springframework.test.ioc;

import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;
import org.junit.Test;
import org.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.springframwork.beans.factory.xml.XmlBeanDefinitionReader;

import static org.junit.jupiter.api.Assertions.*;

public class XmlFileDefinitionBeanTest {

    @Test
    public void testXmlFile(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) factory.getBean("person");
        assertEquals("derek",person.getName());
        assertEquals("porsche", person.getCar().getBrand());

        Car car = (Car) factory.getBean("car");
        assertNotNull(car);
        assertEquals("porsche", car.getBrand());
    }
}
