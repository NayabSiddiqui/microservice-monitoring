package org.build.microservicesmonitoring.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.build.microservicesmonitoring.books.domain.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    List<Book> getAllBooks() throws IOException, InterruptedException {
        InputStream stream = getClass().getResourceAsStream("/response/books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Thread.sleep(500);
        return Arrays.asList(objectMapper.readValue(stream, Book[].class));
    }

    List<String> getAllCountriesForBooks() throws IOException, InterruptedException {
        InputStream stream = getClass().getResourceAsStream("/response/books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = Arrays.asList(objectMapper.readValue(stream, Book[].class));
        Thread.sleep(1000);
        return books.stream()
                .map(book -> book.getCountry())
                .collect(Collectors.toList());
    }
}
