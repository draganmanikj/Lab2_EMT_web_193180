package mk.ukim.finki.demo.service;

import mk.ukim.finki.demo.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findlAll();

    Author findById(Long id);

    Author findByNameAndSurname(String name, String surname);

    Author create(String name, String surname);

    Author save(String name, String surname, Long countryId);

    Author deleteById(Long id);
}
