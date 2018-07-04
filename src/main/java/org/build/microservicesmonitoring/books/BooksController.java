package org.build.microservicesmonitoring.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "books")
public class BooksController {

    private final BooksService service;

    @Autowired
    public BooksController(BooksService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() throws IOException {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @GetMapping(path = "/countries")
    public ResponseEntity<?> getBooksByCountry() throws IOException {
        return ResponseEntity.ok(service.getAllCountriesForBooks());
    }
}
