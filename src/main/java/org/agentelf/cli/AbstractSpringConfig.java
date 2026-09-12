package org.agentelf.cli;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "org.agentelf")
public abstract class AbstractSpringConfig {

    @Bean
    public ApplyTemplate applyTemplate() {
        return new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/template"));
    }

}
