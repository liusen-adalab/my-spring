package org.springframwork.beans.factory.config;

import org.springframwork.beans.BeansException;

public interface BeanPostProcessor {

    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
