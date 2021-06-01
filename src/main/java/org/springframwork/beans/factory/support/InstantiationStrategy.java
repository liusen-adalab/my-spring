package org.springframwork.beans.factory.support;

import org.springframwork.beans.BeansException;
import org.springframwork.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
