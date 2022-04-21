package mk.ukim.finki.demo.service;

import mk.ukim.finki.demo.model.Author;
import mk.ukim.finki.demo.model.Book;
import mk.ukim.finki.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Book findByName(String name);

    Book save(String name, Category category, String aname, String asurname, Integer availableCopies);

    Book edit(Long id, String name, Category category, String aname, String asurname, Integer availableCopies);

    Book deleteById(Long id);

    List<Book> listBooksByNameAndCategory(String name, Category category);

    Book taken(Long id);
}
