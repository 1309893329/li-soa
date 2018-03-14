package com.li.soa.spring.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.li.soa.configBean.Protocol;
import com.li.soa.configBean.Reference;
import com.li.soa.configBean.Registry;
import com.li.soa.configBean.Service;

public class SOANamespaceHandler extends NamespaceHandlerSupport {
    
    public void init() {
        registerBeanDefinitionParser("registry",
                new RegistryBeanDefinitionParse(Registry.class));
        registerBeanDefinitionParser("protocol",
                new ProtocolBeanDefinitionParse(Protocol.class));
        registerBeanDefinitionParser("reference",
                new ReferenceBeanDifinitionParse(Reference.class));
        registerBeanDefinitionParser("service", 
        		new ServiceBeanDefinitionParse(Service.class));
        
    }
    
}
