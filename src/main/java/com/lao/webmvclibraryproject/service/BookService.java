package com.lao.webmvclibraryproject.service;

import com.lao.webmvclibraryproject.model.Book;
import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Transactional(readOnly = true)
    public Book getBook(int id) {
        return bookRepository.getReferenceById(id);
    }
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<User> getBookOwner(int id) {
        return Optional.ofNullable(bookRepository.findById(id).map(Book::getOwner).orElse(null));
    }

    @Transactional
    public void releaseBook(int id) {
        Book bookToBeUpdated = bookRepository.getReferenceById(id);
        bookToBeUpdated.setOwner(null);
    }

    @Transactional
    public void assignReader(int id, User selectedUser) {
        Book bookToBeUpdated = getBook(id);
        bookToBeUpdated.setOwner(selectedUser);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = bookRepository.getReferenceById(id);
        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());
        bookRepository.save(book);
    }

}
