package org.springframwork.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframwork.beans.BeansException;
import org.springframwork.beans.PropertyValue;
import org.springframwork.beans.factory.config.AutowireCapableBeanFactory;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.BeanPostProcessor;
import org.springframwork.beans.factory.config.BeanReference;

public abstract class AbstractAutowireCapableFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(name, beanDefinition);
    }

    private Object doCreateBean(String name, BeanDefinition beanDefinition) {
        Object bean;
        try {
            bean = instantiationStrategy.instantiate(beanDefinition);
            applyPropertyValues(name, bean, beanDefinition);

            initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of org.springframwork.test.ioc.bean failed", e);
        }
        addSingleton(name, bean);

        return bean;
    }

    //todo
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        //todo
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        wrappedBean = applyBeanPostProcessorAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(
            Object existingBean,
            String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(
            Object existingBean,
            String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void invokeInitMethods(String beanName,
                                   Object wrappedBean,
                                   BeanDefinition beanDefinition) {
        //todo

    }

    protected void applyPropertyValues(
            String name,
            Object bean,
            BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, propertyValue.getName(), value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for org.springframwork.test.ioc.bean: " + name, e);
        }
    }


}
