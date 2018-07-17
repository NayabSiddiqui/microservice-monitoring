package org.build.countriesapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.build.countriesapi.domain.Country;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class CountriesService {

    public List<Country> getAllCountries() throws IOException, InterruptedException {
        InputStream stream = getClass().getResourceAsStream("/response/all-countries.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Thread.sleep(500);
        return Arrays.asList(objectMapper.readValue(stream, Country[].class));
    }
}
