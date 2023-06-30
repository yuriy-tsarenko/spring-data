package com.go.it.spring.data.configuration;

import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class ContextFactory implements ApplicationContextFactory {

    @Override
    public ConfigurableApplicationContext create(WebApplicationType webApplicationType) {
        LoggerConfiguration.setupLogger();
        return ApplicationContextFactory.DEFAULT.create(webApplicationType);
    }
}
