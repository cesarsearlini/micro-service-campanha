/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.time;

import br.com.ns.time.model.Time;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Searlini
 */
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class TimeAplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeAplication.class, args);
        rotinaBancoDeDados();
    }

    @Bean
    protected DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PropertiesConfig.DB_DRIVER);
        dataSource.setUrl(PropertiesConfig.DB_HOST_NAME);
        dataSource.setUsername(PropertiesConfig.DB_USER);
        dataSource.setPassword(PropertiesConfig.DB_PASS);
        return dataSource;
    }

    private static void rotinaBancoDeDados() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(PropertiesConfig.DB_HOST_NAME, PropertiesConfig.DB_USER, PropertiesConfig.DB_PASS);
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    } 

}
