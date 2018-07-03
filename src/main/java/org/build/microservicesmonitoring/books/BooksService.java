package org.build.microservicesmonitoring.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.build.microservicesmonitoring.books.domain.Book;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class BooksService {

    Book[] getAllBooks() throws IOException {
        File file = new File(getClass().getResource("/response/books.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        Book[] books = objectMapper.readValue(file, Book[].class);
        return books;
    }
}
