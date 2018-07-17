package org.build.microservicesmonitoring.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.build.microservicesmonitoring.books.domain.Book;
import org.build.microservicesmonitoring.countries.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BooksService {

    private final CountryClient countryClient;

    @Autowired
    public BooksService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    List<Book> getAllBooks() throws IOException, InterruptedException {
        InputStream stream = getClass().getResourceAsStream("/response/books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Thread.sleep(500);
        return Arrays.asList(objectMapper.readValue(stream, Book[].class));
    }

    List<String> getAllCountriesForBooks() throws IOException, InterruptedException {
        return countryClient.getAllCountries().stream()
                .map(country -> country.getName())
                .collect(toList());
    }
}
