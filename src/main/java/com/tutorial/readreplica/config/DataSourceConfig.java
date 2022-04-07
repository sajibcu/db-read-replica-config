package com.tutorial.readreplica.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment environment;

    private static final String PRIMARY_DATASOURCE_PREFIX = "spring.datasource";
    private static final String REPLICA_DATASOURCE_PREFIX = "spring.datasource-read";

    @Bean
    @Primary
    public DataSource dataSource() {
        final RoutingDataSource routingDataSource = new RoutingDataSource();

        final DataSource primaryDataSource = buildDataSource( "Primary", PRIMARY_DATASOURCE_PREFIX );
        final DataSource replicaDataSource = buildDataSource( "Replica", REPLICA_DATASOURCE_PREFIX );

        final Map<Object, Object> targetDataSource = new HashMap<>();

        targetDataSource.put( RoutingDataSource.Route.PRIMARY, primaryDataSource );
        targetDataSource.put( RoutingDataSource.Route.REPLICA, replicaDataSource );

        routingDataSource.setTargetDataSources( targetDataSource );
        routingDataSource.setDefaultTargetDataSource( primaryDataSource );

        return routingDataSource;

    }

    private DataSource buildDataSource( String poolName, String dataSourcePrefix ) {

        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName( poolName );
        hikariConfig.setJdbcUrl( environment.getProperty( String.format( "%s.url", dataSourcePrefix ) ) );
        hikariConfig.setUsername( environment.getProperty( String.format( "%s.username", dataSourcePrefix ) ) );
        hikariConfig.setPassword( environment.getProperty( String.format( "%s.password", dataSourcePrefix ) ) );
        hikariConfig.setDriverClassName( environment.getProperty( String.format( "%s.driver-class-name", dataSourcePrefix ) ) );

        return new HikariDataSource( hikariConfig );
    }

}
