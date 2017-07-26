/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 *
 * @author Cesar Searlini
 */
public class XGetURL {

    private static final String TRACE_LOG = "Trace log: URL {} ";
    private static final Logger log = LoggerFactory.getLogger(Object.class);

    /**
     *
     * @param serviceHost
     * @param servicePort
     * @param controller
     * @return
     */
    public static String getURL(String serviceHost, Long servicePort, String controller) {
        String url;
        url = String.format("http://%s:%s/" + controller + "/", serviceHost, servicePort);
        log.trace(TRACE_LOG, url);
        return url;
    }

    /**
     *
     * @param loadBalancerClient
     * @param aplicationName
     * @param controller
     * @return
     */
    public static String getURLRibbon(LoadBalancerClient loadBalancerClient, String aplicationName, String controller) {
        String url;
        ServiceInstance instance = loadBalancerClient.choose(aplicationName);
        url = String.format("http://%s:%s/" + controller + "/", instance.getHost(), instance.getPort());
        log.trace(TRACE_LOG, url);
        return url;
    }

}
