/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.campanha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Searlini
 */
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class CampanhaAplication {

    public static void main(String[] args) {
        SpringApplication.run(CampanhaAplication.class, args);        
    }       

}
