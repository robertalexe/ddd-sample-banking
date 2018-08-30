package com.robert.ddd;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.robert.ddd"}
)
@EntityScan(
        basePackages = {"com.robert.ddd"}
)
@ComponentScan(
        basePackages = {"com.robert.ddd"}
)
public class PersistenceConfig {
    public PersistenceConfig() {}
}
