package dev.clickhouse.migration.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.DataSourceClosingSpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yaml");

        liquibase.setDataSource(dataSource);

        return liquibase;
    }

    @Bean
    public DataSource method(){
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setPassword("1231234");
        properties.setUser("dev");
        ClickHouseDataSource dataSource = new ClickHouseDataSource("jdbc:clickhouse://0.0.0.0:8123/test", properties);

        return dataSource;
    }

}
