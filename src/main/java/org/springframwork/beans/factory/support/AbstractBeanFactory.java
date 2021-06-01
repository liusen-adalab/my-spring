package org.springframwork.beans.factory.support;

import org.springframwork.beans.BeansException;
import org.springframwork.beans.factory.BeanFactory;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.BeanFactoryPostProcessor;
import org.springframwork.beans.factory.config.BeanPostProcessor;
import org.springframwork.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory, ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);

        return createBean(name, beanDefinition);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeansException;

    protected  abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

}
