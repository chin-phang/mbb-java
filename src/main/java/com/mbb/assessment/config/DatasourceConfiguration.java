package com.mbb.assessment.config;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.mbb.assessment.entity")
@EnableJpaRepositories(
    basePackages = {"com.mbb.assessment.repository"},
    repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
public class DatasourceConfiguration {

}
