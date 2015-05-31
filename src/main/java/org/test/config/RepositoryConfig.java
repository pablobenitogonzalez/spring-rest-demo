package org.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("org.test.repository")
public class RepositoryConfig {
    // 
}
