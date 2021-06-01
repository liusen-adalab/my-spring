package org.springframwork.beans.factory.config;

import org.springframwork.beans.BeansException;
import org.springframwork.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessorBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException;
}
