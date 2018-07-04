package org.build.microservicesmonitoring.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.build.microservicesmonitoring.books.domain.Book;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BooksService {

    List<Book> getAllBooks() throws IOException {
        File file = new File(getClass().getResource("/response/books.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(file, Book[].class));
    }

    List<String> getAllCountriesForBooks() throws IOException {
        File file = new File(getClass().getResource("/response/books.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = Arrays.asList(objectMapper.readValue(file, Book[].class));
        return books.stream()
                .map(book -> book.getCountry())
                .collect(Collectors.toList());
    }
}
