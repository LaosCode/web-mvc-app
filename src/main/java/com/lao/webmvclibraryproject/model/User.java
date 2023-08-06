package com.lao.webmvclibraryproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "user_entity")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Min(value = 1900, message = "Age should be greater than 0")
    @Column(name = "year")
    private int year;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Book> bookList;

}
