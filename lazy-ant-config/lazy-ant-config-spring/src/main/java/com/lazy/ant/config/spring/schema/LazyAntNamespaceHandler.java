package com.lazy.ant.config.spring.schema;

import com.lazy.ant.config.ApplicationConfig;
import com.lazy.ant.config.ProtocolConfig;
import com.lazy.ant.config.RegistryConfig;
import com.lazy.ant.config.spring.ReferenceBean;
import com.lazy.ant.config.spring.ServiceBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class LazyAntNamespaceHandler extends NamespaceHandlerSupport {


    @Override
    public void init() {
        registerBeanDefinitionParser("application", new LazyAntBeanDefinitionParser(ApplicationConfig.class));
        registerBeanDefinitionParser("registry", new LazyAntBeanDefinitionParser(RegistryConfig.class));
        registerBeanDefinitionParser("protocol", new LazyAntBeanDefinitionParser(ProtocolConfig.class));
        registerBeanDefinitionParser("service", new LazyAntBeanDefinitionParser(ServiceBean.class));
        registerBeanDefinitionParser("reference", new LazyAntBeanDefinitionParser(ReferenceBean.class));
    }
}
