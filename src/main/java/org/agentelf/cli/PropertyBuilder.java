package org.agentelf.cli;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class PropertyBuilder {

    private final List<String> profileList = new ArrayList<>();

    public PropertyBuilder addProfile(String name) {
        profileList.add(name);
        return this;
    }

    public PropertySourcesPlaceholderConfigurer build() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        Resource[] resourceArray = new Resource[profileList.size()];
        int index = 0;
        for(String profile : profileList) {
            resourceArray[index] = new FileSystemResource(".agentelf/config/" + profile +  ".yml");
            index++;
        }
        yaml.setResources(resourceArray);
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

}
