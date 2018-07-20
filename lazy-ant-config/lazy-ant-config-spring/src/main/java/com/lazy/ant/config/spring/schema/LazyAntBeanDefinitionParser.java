package com.lazy.ant.config.spring.schema;

import com.lazy.ant.common.logger.Logger;
import com.lazy.ant.common.logger.LoggerFactory;
import com.lazy.ant.common.tools.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

/**
 * <p>
 *     XSD Spring Bean 解析实现
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class LazyAntBeanDefinitionParser implements BeanDefinitionParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyAntBeanDefinitionParser.class);
    private final Class<?> beanClass;

    public LazyAntBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return this.parse(element, parserContext, beanClass);
    }

    private BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        if ((id == null || id.length() == 0)) {
            String generatedBeanName = element.getAttribute("name");
            if (generatedBeanName == null || generatedBeanName.length() == 0) {
                generatedBeanName = element.getAttribute("interface");
            }
            if (generatedBeanName == null || generatedBeanName.length() == 0) {
                generatedBeanName = beanClass.getName();
            }
            id = generatedBeanName;
            int counter = 2;
            while (parserContext.getRegistry().containsBeanDefinition(id)) {
                id = generatedBeanName + (counter++);
            }
        }
        if (id.length() > 0) {
            if (parserContext.getRegistry().containsBeanDefinition(id)) {
                throw new IllegalStateException("Duplicate spring bean id " + id);
            }
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }
        Method[] methodList = beanClass.getMethods();
        for (Method setter : methodList) {
            String name = setter.getName();
            if (name.length() > 3 && name.startsWith("set")
                    && Modifier.isPublic(setter.getModifiers())
                    && setter.getParameterTypes().length == 1) {
                Class<?> type = setter.getParameterTypes()[0];
                String property = StringUtils.camelToSplitName(name.substring(3, 4).toLowerCase() + name.substring(4), "-");
                String value = element.getAttribute(property);
                if (value == null) {
                    continue;
                }
                Method getter = null;
                try {
                    getter = beanClass.getMethod("get" + name.substring(3), new Class<?>[0]);
                } catch (NoSuchMethodException e) {
                    try {
                        getter = beanClass.getMethod("is" + name.substring(3), new Class<?>[0]);
                    } catch (NoSuchMethodException e2) {
                    }
                }
                if (getter == null
                        || !Modifier.isPublic(getter.getModifiers())
                        || !type.equals(getter.getReturnType())) {
                    continue;
                }
                value = value.trim();
                Object reference = value;
                if ("ref".equals(property) && parserContext.getRegistry().containsBeanDefinition(value)) {
                    BeanDefinition refBean = parserContext.getRegistry().getBeanDefinition(value);
                    if (!refBean.isSingleton()) {
                        throw new IllegalStateException("The exported service ref " + value + " must be singleton! Please set the " + value + " bean scope to singleton, eg: <bean id=\"" + value + "\" scope=\"singleton\" ...>");
                    }
                    reference = new RuntimeBeanReference(value);
                }
                beanDefinition.getPropertyValues().addPropertyValue(property, reference);
            }
        }
        return beanDefinition;
    }
}
