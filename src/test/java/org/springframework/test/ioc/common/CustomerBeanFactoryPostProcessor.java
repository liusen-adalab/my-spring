package org.springframework.test.ioc.common;

import org.springframwork.beans.BeansException;
import org.springframwork.beans.PropertyValue;
import org.springframwork.beans.PropertyValues;
import org.springframwork.beans.factory.ConfigurableListableBeanFactory;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.BeanFactoryPostProcessor;

public class CustomerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        BeanDefinition beanDefinition = factory.getBeanDefinition("person");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addProperty(new PropertyValue("name", "ivy"));
    }
}
