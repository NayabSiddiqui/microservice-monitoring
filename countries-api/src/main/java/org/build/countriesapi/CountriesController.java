package org.build.countriesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "countries")
public class CountriesController {

    private final CountriesService service;

    @Autowired
    public CountriesController(CountriesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllCountries() throws IOException, InterruptedException {
        return ResponseEntity.ok(service.getAllCountries());
    }
}
