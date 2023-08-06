package com.lao.webmvclibraryproject.controller;


import com.lao.webmvclibraryproject.model.Book;
import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.service.BookService;
import com.lao.webmvclibraryproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService booksService;
    private final UserService userService;



    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allbooks", booksService.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("takenBooks", userService.getBooksByUserName(currentPrincipalName));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("book", booksService.getBook(id));

        Optional<User> bookOwner = booksService.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());

        } else {
            model.addAttribute("users", userService.findAll());
        }
        return "books/show";
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("/{id}/forcedRelease")
    public String forcedRelease(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (booksService.getBookOwner(id).get().getName().equals(currentPrincipalName)) {
            booksService.releaseBook(id);
        }
        return "redirect:/books";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/assignByAdmin")
    public String assignByAdmin(@PathVariable("id") int id, @ModelAttribute("person") User selectedUser) {
        booksService.assignReader(id, selectedUser);
        return "redirect:/books";
    }
    @PostMapping("/{id}/assignByReader")
    public String assignByUser(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        booksService.assignReader(id, userService.findUserByName(currentPrincipalName).get());
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Book book,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "books/new";
        }

        booksService.addBook(book);
        return "redirect:/books";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
