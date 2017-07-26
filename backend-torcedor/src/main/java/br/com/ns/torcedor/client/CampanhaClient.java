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
public class CampanhaClient {

    private final Logger log = LoggerFactory.getLogger(CampanhaClient.class);

    public static class CampanhaPagedResources extends PagedResources<Campanha> {

    }

    private final RestTemplate restTemplate;
    private String campanhaServiceHost;
    private final long campanhaServicePort;
    private final boolean useRibbon;
    private LoadBalancerClient loadBalancerClient;
    private Collection<Campanha> campanhasCache = null;

    @Autowired
    public CampanhaClient(
            @Value("${catalog.service.host:campanha}") String campanhaServiceHost,
            @Value("${catalog.service.port:8080}") long campanhaServicePort,
            @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
        super();
        this.restTemplate = getRestTemplate();
        this.campanhaServicePort = campanhaServicePort;
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
            url = XGetURL.getURLRibbon(loadBalancerClient, "CAMPANHA", controller);
        } else {
            url = XGetURL.getURL(campanhaServiceHost, campanhaServicePort, controller);
        }
        return url;
    }

    @HystrixCommand(fallbackMethod = "getCampanhasCache", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")})
    public Collection<Campanha> findAll() {
        PagedResources<Campanha> pagedResources = restTemplate.getForObject(
                getURL(""), PagedResources.class);
        this.campanhasCache = pagedResources.getContent();
        return pagedResources.getContent();
    }

    private Collection<Campanha> getCampanhasCache() {
        return campanhasCache;
    }

    @HystrixCommand(fallbackMethod = "getOneCache", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")})
    public Campanha getOne(long idCampanha) {
        return restTemplate.getForObject(getURL("") + idCampanha, Campanha.class);
    }

    public Campanha getOneCache(long idCampanha) {
        return campanhasCache.stream().filter(i -> (i.getId() == idCampanha))
                .findFirst().get();
    }

}
