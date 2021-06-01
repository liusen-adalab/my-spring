package org.springframwork.beans.factory.config;

import org.springframwork.beans.PropertyValues;

public class BeanDefinition {
    private final Class<?> beanClass;
    private final PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, new PropertyValues());
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
