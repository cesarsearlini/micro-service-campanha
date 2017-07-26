/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.client;

import br.com.ns.torcedor.util.XGetURL;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Cesar Searlini
 */
@Component
public class TimeClient {

    private final Logger log = LoggerFactory.getLogger(TimeClient.class);

    public static class TimePagedResources extends PagedResources<Time> {

    }

    private final RestTemplate restTemplate;
    private String timeServiceHost;
    private final long timeServicePort;
    private final boolean useRibbon;
    private LoadBalancerClient loadBalancerClient;
    private Collection<Time> timesCache = null;

    @Autowired
    public TimeClient(
            @Value("${catalog.service.host:time}") String timeServiceHost,
            @Value("${catalog.service.port:8080}") long timeServicePort,
            @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
        super();
        this.restTemplate = getRestTemplate();
        this.timeServicePort = timeServicePort;
        this.useRibbon = useRibbon;
    }

    @Autowired(required = false)
    public void setLoadBalancer(LoadBalancerClient loadBalancer) {
        this.loadBalancerClient = loadBalancer;
    }

    protected RestTemplate getRestTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
        converter.setObjectMapper(mapper);

        return new RestTemplate(
                Collections.<HttpMessageConverter<?>>singletonList(converter));
    }

    private String getURL(String controller) {
        String url;
        if (useRibbon) {
            url = XGetURL.getURLRibbon(loadBalancerClient, "TIME", controller);
        } else {
            url = XGetURL.getURL(timeServiceHost, timeServicePort, controller);
        }
        return url;
    }

    @HystrixCommand(fallbackMethod = "getTimesCache", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")})
    public Collection<Time> findAll() {
        PagedResources<Time> pagedResources = restTemplate.getForObject(
                getURL(""), TimePagedResources.class);
        this.timesCache = pagedResources.getContent();
        return pagedResources.getContent();
    }

    private Collection<Time> getTimesCache() {
        return timesCache;        
    }

    @HystrixCommand(fallbackMethod = "getOneCache", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")})
    public Time getOne(long idTime) {
        return restTemplate.getForObject(getURL("") + idTime, Time.class);
    }

    public Time getOneCache(long idTime) {
        return timesCache.stream().filter(i -> (i.getId() == idTime))
                .findFirst().get();
    }

}
