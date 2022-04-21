package mk.ukim.finki.demo.service;

import mk.ukim.finki.demo.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(Long id);

    Country save(String name, String continent);


}
