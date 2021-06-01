package org.springframework.test.ioc;

import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframwork.beans.PropertyValue;
import org.springframwork.beans.PropertyValues;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.BeanReference;
import org.springframwork.beans.factory.support.DefaultListableBeanFactory;
import static org.assertj.core.api.Assertions.*;

public class PopulateBeanWithPropertyValuesTest {

    private DefaultListableBeanFactory factory;

    @Before
    public void createPerson(){
        factory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("age", 18));
        propertyValues.addProperty(new PropertyValue("name", "tom"));
        propertyValues.addProperty(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);

        factory.registryBeanDefinition("person", beanDefinition);
    }

    @Test
    public void populateBeanWithProperty(){
        Person person = (Person) factory.getBean("person"); // lazy load

        assertThat(person.getName()).isEqualTo("tom");
        assertThat(person.getAge()).isEqualTo(18);
    }


    @Test
    public void populateBeanWithBean(){
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("brand", "porsche"));
        BeanDefinition definition = new BeanDefinition(Car.class, propertyValues);
        factory.registryBeanDefinition("car", definition);

        Person person = (Person) factory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("tom");
        assertThat(person.getAge()).isEqualTo(18);
        Car car = (Car) factory.getBean("car");
        assertThat(car).isNotNull();
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
