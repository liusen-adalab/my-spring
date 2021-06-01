package org.springframwork.beans.factory.config;

import org.springframwork.beans.BeansException;

public interface AutowireCapableBeanFactory extends ConfigurableBeanFactory {

    Object applyBeanPostProcessorBeforeInitialization(
            Object existingBean,
            String beanName
    ) throws BeansException;

    Object applyBeanPostProcessorAfterInitialization(
            Object existingBean,
            String beanName
    ) throws BeansException;
}
