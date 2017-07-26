/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.time;

/**
 *
 * @author Cesar Searlini
 */
public class PropertiesConfig {

    private PropertiesConfig() {
    }

    protected static final String DB_DRIVER = "org.postgresql.Driver";
    protected static final String DB_HOST = "jdbc:postgresql://localhost:5432/";
    protected static final String DB_NAME = "nsjava";
    protected static final String DB_USER = "postgres";
    protected static final String DB_PASS = "postgres";
    protected static final String DB_HOST_NAME = DB_HOST + DB_NAME;
}
