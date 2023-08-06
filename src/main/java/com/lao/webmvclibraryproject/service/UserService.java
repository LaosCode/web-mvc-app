package com.lao.webmvclibraryproject.service;

import com.lao.webmvclibraryproject.model.Book;
import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByUserId(int id) {
        Optional<User> person = userRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookList());

//            person.get().getBookList().forEach(book -> {
//                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
//                // 864000000 милисекунд = 10 суток
//                if (diffInMillies > 864000000)
//                    book.setExpired(true); // книга просрочена
//            });

            return person.get().getBookList();
        }
        return Collections.EMPTY_LIST;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByUserName(String name) {
        Optional<User> person = userRepository.findUserByName(name);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookList());

//            person.get().getBookList().forEach(book -> {
//                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
//                // 864000000 милисекунд = 10 суток
//                if (diffInMillies > 864000000)
//                    book.setExpired(true); // книга просрочена
//            });

            return person.get().getBookList();
        }
        return Collections.EMPTY_LIST;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Transactional
    public void update(int id, User user) {
        User userToBeUpdated = userRepository.findById(id).get();
        user.setBookList(userToBeUpdated.getBookList());
        userRepository.save(user);
    }
}
