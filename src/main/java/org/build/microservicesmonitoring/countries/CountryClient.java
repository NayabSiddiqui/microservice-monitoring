package org.build.microservicesmonitoring.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class CountryClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${countries-api.base-url}")
    private String baseUrl;

    @Value("${countries-api.endpoints.countries}")
    private String countriesEndpoint;

    public List<Country> getAllCountries() {
        System.out.println("fetching countries on thread " + Thread.currentThread());
        Country[] countries = restTemplate.getForObject(baseUrl + countriesEndpoint, Country[].class);
        return Arrays.asList(countries);
    }
}
