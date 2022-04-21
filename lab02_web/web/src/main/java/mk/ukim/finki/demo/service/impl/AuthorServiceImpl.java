package mk.ukim.finki.demo.service.impl;

import mk.ukim.finki.demo.model.Author;
import mk.ukim.finki.demo.model.Country;
import mk.ukim.finki.demo.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.demo.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.demo.repository.jpa.AuthorRepository;
import mk.ukim.finki.demo.repository.jpa.CountryRepository;
import mk.ukim.finki.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    public final AuthorRepository authorRepository;
    public final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findlAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(()
                -> new AuthorNotFoundException());
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        return this.authorRepository.findByNameAndSurname(name, surname).orElse( create(name,surname));
    }

    @Override
    public Author create(String name, String surname) {
        Author author = new Author(name, surname);
        return this.authorRepository.save(author);
    }

    @Override
    public Author save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(()
                -> new CountryNotFoundException());
        Author author = new Author(name, surname, country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author deleteById(Long id) {
        Author author = this.findById(id);
        this.authorRepository.delete(author);
        return author;
    }
}
