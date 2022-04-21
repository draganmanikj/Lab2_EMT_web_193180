package mk.ukim.finki.demo.service.impl;

import mk.ukim.finki.demo.model.Country;
import mk.ukim.finki.demo.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.demo.repository.jpa.CountryRepository;
import mk.ukim.finki.demo.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    public final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(()
                -> new CountryNotFoundException());
    }

    @Override
    public Country save(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }
}
