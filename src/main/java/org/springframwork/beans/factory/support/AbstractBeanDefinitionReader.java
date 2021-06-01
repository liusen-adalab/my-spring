package org.springframwork.beans.factory.support;

import org.springframwork.core.io.DefaultResourceLoader;
import org.springframwork.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;

    private  ResourceLoader loader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader loader) {
        this.registry = registry;
        this.loader = loader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String[] locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public void setLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return loader;
    }
}
