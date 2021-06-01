package org.springframwork.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframwork.beans.BeansException;
import org.springframwork.beans.PropertyValue;
import org.springframwork.beans.factory.config.BeanDefinition;
import org.springframwork.beans.factory.config.BeanReference;
import org.springframwork.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframwork.beans.factory.support.BeanDefinitionRegistry;
import org.springframwork.core.io.Resource;
import org.springframwork.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader loader) {
        super(registry, loader);
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {

            if (childNodes.item(i) instanceof Element
                    && BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute(ID_ATTRIBUTE);
                String name = bean.getAttribute(NAME_ATTRIBUTE);
                String className = bean.getAttribute(CLASS_ATTRIBUTE);

                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new BeansException("Cannot find class [" + className + "]");
                }

                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }

                BeanDefinition beanDefinition = new BeanDefinition(clazz);

                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    if (bean.getChildNodes().item(j) instanceof Element &&
                    PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())){
                        Element property = (Element) bean.getChildNodes().item(j);
                        String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                        String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                        String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                        if(StrUtil.isEmpty(nameAttribute)){
                            throw new BeansException("The name attribute cannot be null or empty");
                        }
                        Object value = valueAttribute;
                        if(StrUtil.isNotEmpty(refAttribute)){
                            value = new BeanReference(refAttribute);
                        }
                        PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                        beanDefinition.getPropertyValues().addProperty(propertyValue);
                    }

                }

                if(getRegistry().containsBeanDefinition(beanName)){
                    throw new BeansException("Duplicate org.springframwork.test.ioc.bean name [" + beanName + "]");
                }
                getRegistry().registryBeanDefinition(beanName, beanDefinition);
            }
        }
    }
}
