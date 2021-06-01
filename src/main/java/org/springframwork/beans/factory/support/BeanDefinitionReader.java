package org.springframwork.beans.factory.support;

import org.springframwork.core.io.Resource;
import org.springframwork.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String[] locations);
}
