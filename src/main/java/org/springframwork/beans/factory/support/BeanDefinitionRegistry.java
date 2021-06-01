package org.springframwork.beans.factory.support;

import org.springframwork.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String name);
}
