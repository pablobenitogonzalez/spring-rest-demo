package org.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@Import(value = {
        DataSourceConfig.class,
        InfrastructureConfig.class,
        RepositoryConfig.class,
        ServiceConfig.class,
        //StatelessAuthenticationSecurityConfig.class
} )
public class MainConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MainConfig.class);

    @Autowired
    private Environment env;

    @PostConstruct
    public void initApp() {
        LOG.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            LOG.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                LOG.info("Detected Spring profile: {}", profile);
            }
        }
    }
}
