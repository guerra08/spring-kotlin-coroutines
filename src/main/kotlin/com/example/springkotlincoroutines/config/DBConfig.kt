package com.example.springkotlincoroutines.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class DBConfig {
    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(CompositeDatabasePopulator()
                .apply {
                    addPopulators(ResourceDatabasePopulator(ClassPathResource(
                        "sql/schema.sql"
                    )))
                }
            )
        }
        return initializer
    }
}

