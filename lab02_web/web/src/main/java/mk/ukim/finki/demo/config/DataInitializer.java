package mk.ukim.finki.demo.config;

import mk.ukim.finki.demo.model.Category;
import mk.ukim.finki.demo.service.AuthorService;
import mk.ukim.finki.demo.service.BookService;
import mk.ukim.finki.demo.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    private final BookService bookService;

    private final CountryService countryService;

    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {

        this.countryService.save("USA","America");
        this.countryService.save("India","Asia");

        this.authorService.save("Stephen","King", 1L);
        this.authorService.save("Jeet","Thayil",2L);

        this.bookService.save("It", Category.NOVEL,"Stephen","King",2);
        this.bookService.save("The Shining", Category.NOVEL,"Stephen","King",5);
        this.bookService.save("Names of the Women", Category.NOVEL, "Jeet","Thayil", 3);
        this.bookService.save("Cell", Category.THRILER, "Stephen","King", 8);
        this.bookService.save("The Eyes Of The Dragon", Category.FANTASY, "Stephen","King",4);

    }
}
