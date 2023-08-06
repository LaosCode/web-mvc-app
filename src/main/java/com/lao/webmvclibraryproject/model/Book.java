package com.lao.webmvclibraryproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity(name = "book_entity")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private User owner;


}
