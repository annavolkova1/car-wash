package com.car_wash.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.car_wash.domain")
@EnableJpaRepositories("com.car_wash.repos")
@EnableTransactionManagement
public class DomainConfig {

}
