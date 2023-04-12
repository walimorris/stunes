package com.morris.stunes.util;

import com.morris.stunes.configuration.Properties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class SpringApplicationPropertiesHelper {

    public static final String JPA_HIBERNATE_DIALECT = "hibernate-dialect";
    public static final String JPA_SHOW_SQL = "show-sql";
    public static final String JPA_DDL_AUTO = "ddl-auto";

    private final Map<String, String> springProperties;

    public SpringApplicationPropertiesHelper() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties springPropertiesContext = context.getBean(Properties.class);
        this.springProperties = springPropertiesContext.JPADatasourceProperties();
        context.close();
    }

    public String getJpaHibernateDialect() {
        return springProperties.get(JPA_HIBERNATE_DIALECT);
    }

    public String getJpaShowSql() {
        return springProperties.get(JPA_SHOW_SQL);
    }

    public String getJpaDdlAuto() {
        return springProperties.get(JPA_DDL_AUTO);
    }
}
