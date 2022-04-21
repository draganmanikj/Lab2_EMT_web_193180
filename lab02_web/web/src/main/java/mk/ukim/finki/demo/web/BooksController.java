package mk.ukim.finki.demo.web;

import mk.ukim.finki.demo.model.Author;
import mk.ukim.finki.demo.model.Book;
import mk.ukim.finki.demo.model.Category;
import mk.ukim.finki.demo.service.AuthorService;
import mk.ukim.finki.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class BooksController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping({"/","/books"})
    public String showBooks(@RequestParam(required = false) String name,
                            @RequestParam(required = false) Category category,
                            Model model)
    {
        List<Book> books;
        if(name == null && category == null)
        {
            books = this.bookService.findAll();
        }
        else{
            books = this.bookService.listBooksByNameAndCategory(name, category);
        }
        List<Author> authors = authorService.findlAll();
        List<Category> categories = new ArrayList<>();
        Category a = Category.NOVEL;
        Category b = Category.THRILER;
        Category c = Category.HISTORY;
        Category d = Category.FANTASY;
        Category e = Category.BIOGRAPHY;
        Category f = Category.CLASSICS;
        Category g = Category.DRAMA;
        categories.add(a);
        categories.add(b);
        categories.add(c);
        categories.add(d);
        categories.add(e);
        categories.add(f);
        categories.add(g);
        model.addAttribute("books",books);
        model.addAttribute("categories",categories);
        model.addAttribute("authors",authors);
        return "list.html";
    }

    @GetMapping("/books/add")
    public String showAdd(Model model) {
        List<Author> authors = authorService.findlAll();
        List<Category> categories = new ArrayList<>();
        Category a = Category.NOVEL;
        Category b = Category.THRILER;
        Category c = Category.HISTORY;
        Category d = Category.FANTASY;
        Category e = Category.BIOGRAPHY;
        Category f = Category.CLASSICS;
        Category g = Category.DRAMA;
        categories.add(a);
        categories.add(b);
        categories.add(c);
        categories.add(d);
        categories.add(e);
        categories.add(f);
        categories.add(g);
        model.addAttribute("categories",categories);
        model.addAttribute("authors",authors);
        return "form.html";
    }

    @GetMapping("/books/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        List<Author> authors = authorService.findlAll();
        Book book = this.bookService.findById(id);
        model.addAttribute("book",book);
        List<Category> categories = new ArrayList<>();
        Category a = Category.NOVEL;
        Category b = Category.THRILER;
        Category c = Category.HISTORY;
        Category d = Category.FANTASY;
        Category e = Category.BIOGRAPHY;
        Category f = Category.CLASSICS;
        Category g = Category.DRAMA;
        categories.add(a);
        categories.add(b);
        categories.add(c);
        categories.add(d);
        categories.add(e);
        categories.add(f);
        categories.add(g);
        model.addAttribute("categories",categories);
        model.addAttribute("authors",authors);
        return "form.html";
    }

    @GetMapping("/categories")
    public String showCategories(Model model)
    {
        List<Category> categories = new ArrayList<>();
        Category a = Category.NOVEL;
        Category b = Category.THRILER;
        Category c = Category.HISTORY;
        Category d = Category.FANTASY;
        Category e = Category.BIOGRAPHY;
        Category f = Category.CLASSICS;
        Category g = Category.DRAMA;
        categories.add(a);
        categories.add(b);
        categories.add(c);
        categories.add(d);
        categories.add(e);
        categories.add(f);
        categories.add(g);
        model.addAttribute("categories",categories);
        return "categories.html";
    }

    @PostMapping("/books")
    public String create(@RequestParam String name,
                         @RequestParam Category category,
                         @RequestParam String aname,
                         @RequestParam String asurname,
                         @RequestParam Integer availableCopies) {
        this.bookService.save(name,category,aname,asurname,availableCopies);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Category category,
                         @RequestParam String aname,
                         @RequestParam String asurname,
                         @RequestParam Integer availableCopies) {
        this.bookService.edit(id, name, category, aname,asurname, availableCopies);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable String id ) {
        this.bookService.deleteById(Long.parseLong(id));
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/taken")
    public String like(@PathVariable String id) {
        this.bookService.taken(Long.parseLong(id));
        return "redirect:/books";
    }
}
