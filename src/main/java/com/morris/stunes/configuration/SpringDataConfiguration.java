package com.morris.stunes.configuration;

import com.morris.stunes.util.RDSAuroraConnectionHelper;
import com.morris.stunes.util.SpringApplicationPropertiesHelper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("com.morris.stunes")
@EntityScan("com.morris.stunes")
@EnableJpaRepositories("com.morris.stunes.repository")
@PropertySource("classpath:secrets.properties")
public class SpringDataConfiguration {

    private static final String DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String DIALECT = "hibernate.dialect";
    private static final String PACKAGES_TO_SCAN = "com.morris.stunes";

    @Bean
    public DataSource dataSource() {
        return new RDSAuroraConnectionHelper().RDSAuroraDatasource();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(this.entityManagerFactory().getObject()));
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setShowSql(Boolean.getBoolean(new SpringApplicationPropertiesHelper().getJpaShowSql()));
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.put(DDL_AUTO, new SpringApplicationPropertiesHelper().getJpaDdlAuto());
        properties.put(DIALECT, new SpringApplicationPropertiesHelper().getJpaHibernateDialect());
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        return localContainerEntityManagerFactoryBean;
    }
}
