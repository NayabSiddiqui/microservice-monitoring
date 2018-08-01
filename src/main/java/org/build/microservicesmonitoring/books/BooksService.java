package org.build.microservicesmonitoring.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.build.microservicesmonitoring.books.domain.Book;
import org.build.microservicesmonitoring.countries.Country;
import org.build.microservicesmonitoring.countries.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
public class BooksService {

    private final CountryClient countryClient;
    private final Tracer tracer;

    @Autowired
    public BooksService(CountryClient countryClient, Tracer tracer) {
        this.countryClient = countryClient;
        this.tracer = tracer;
    }

    List<Book> getAllBooks() throws IOException, InterruptedException {
        System.out.println("fetching countries from books on thread " + Thread.currentThread());
        InputStream stream = getClass().getResourceAsStream("/response/books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Thread.sleep(800);
        return asList(objectMapper.readValue(stream, Book[].class));
    }

    List<String> getAllCountriesForBooks() throws IOException, InterruptedException {
        System.out.println("On Thread 1" + Thread.currentThread());

        Observable<String> allCountriesObservable = Observable.fromCallable(() -> countryClient.getAllCountries().stream()
                .map(Country::getName)
                .collect(toList()))
                .flatMapIterable(countries -> countries)
                .subscribeOn(Schedulers.io());

        System.out.println("On Thread 2" + Thread.currentThread());

        Observable<String> countriesFromBooksObservable = Observable.fromCallable(() -> getAllBooks().stream()
                .map(Book::getCountry)
                .collect(toList()))
                .flatMapIterable(countries -> countries)
                .subscribeOn(Schedulers.io());

        System.out.println("On Thread 3" + Thread.currentThread());


        Iterable<String> countries = Observable.merge(allCountriesObservable, countriesFromBooksObservable)
                .blockingIterable();

        return newArrayList(countries);
    }
}
