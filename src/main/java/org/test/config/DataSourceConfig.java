package org.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySources(value = {@PropertySource({ "classpath:datasource.properties" })})
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    @Profile("javaee")
    public JndiObjectFactoryBean dataSource() throws IllegalArgumentException {
        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setExpectedType(DataSource.class);
        dataSource.setJndiName(env.getProperty("jdbc.jndiDataSource"));
        return dataSource;
    }

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }
}
