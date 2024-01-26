package mg.finance.apiv.security.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "pgEntityManagerFactory",
        transactionManagerRef = "pgTransactionManager",
        basePackages = {"mg.finance.apiv.annonce", "mg.finance.apiv.security"})
public class PgDatasourceConfiguration {

    @Primary
    @Bean(name="pgProperties")
    @ConfigurationProperties("spring.datasource.pg")
    public DataSourceProperties dataSourceProperties() {

        return new DataSourceProperties();
    }

    @Primary
    @Bean(name="pgDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.pg.hikari")
    public DataSource datasource(@Qualifier("pgProperties") DataSourceProperties properties){

        return properties.initializeDataSourceBuilder().build();
    }
    @Primary
    @Bean
    public JdbcTemplate pgJdbcTemplate(@Qualifier("pgDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name="pgEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("pgDatasource") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("mg.finance.apiv.annonce", "mg.finance.apiv.security").persistenceUnit("pg").build();
    }

    @Primary
    @Bean(name = "pgTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("pgEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {

        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }

}
