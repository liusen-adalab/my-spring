package org.springframwork.beans.factory;

import org.springframwork.beans.BeansException;
import org.springframwork.beans.factory.ListableBeanFactory;
import org.springframwork.beans.factory.config.AutowireCapableBeanFactory;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends AutowireCapableBeanFactory,
        ConfigurableBeanFactory, ListableBeanFactory {

    BeanDefinition getBeanDefinition(String  beanName) throws BeansException;
}
