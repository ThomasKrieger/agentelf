package org.agentelf.cli;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class AppConfig extends AbstractSpringConfig {

   @Bean
    public ApplyTemplate applyTemplate() {
          return new ApplyTemplate(new DefaultMustacheFactory(new File(".agentelf/template" )));
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new FileSystemResource(".agentelf/config.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

}
