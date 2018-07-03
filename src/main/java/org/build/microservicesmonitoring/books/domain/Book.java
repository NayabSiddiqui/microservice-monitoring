package org.build.microservicesmonitoring.books.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private final String author;
    private final String country;
    private final String language;
    private final String link;
    private final int pages;
    private final String title;
    private final int year;

    @JsonCreator
    public Book(@JsonProperty("author") String author,
                @JsonProperty("country") String country,
                @JsonProperty("language") String language,
                @JsonProperty("link") String link,
                @JsonProperty("pages") int pages,
                @JsonProperty("title") String title,
                @JsonProperty("year") int year) {
        this.author = author;
        this.country = country;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }
}
