package mk.ukim.finki.demo.service.impl;

import mk.ukim.finki.demo.model.Author;
import mk.ukim.finki.demo.model.Book;
import mk.ukim.finki.demo.model.Category;
import mk.ukim.finki.demo.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.demo.model.exceptions.BookNotFoundException;
import mk.ukim.finki.demo.repository.jpa.AuthorRepository;
import mk.ukim.finki.demo.repository.jpa.BookRepository;
import mk.ukim.finki.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;
    public final AuthorServiceImpl authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorServiceImpl authorService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(()
                -> new BookNotFoundException());
    }

    @Override
    public Book findByName(String name) {
        return this.bookRepository.findByName(name).orElseThrow(()
                -> new BookNotFoundException());
    }

    @Override
    public Book save(String name, Category category, String aname,String asurname, Integer availableCopies) {

            Author author = this.authorRepository.findByNameAndSurname(aname, asurname).orElseThrow(() ->
                    new AuthorNotFoundException());

         Book book = new Book(name,category, author, availableCopies);
         return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String name, Category category, String aname,String asurname,Integer availableCopies) {
        Book book = this.findById(id);
        Author author = this.authorRepository.findByNameAndSurname(aname, asurname).orElseThrow(()
                -> new AuthorNotFoundException());
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book deleteById(Long id) {
        Book book = this.findById(id);
        this.bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> listBooksByNameAndCategory(String name, Category category) {
        String nameLike = "%" + name + "%";
        if(name != null && category != null)
        {
            return this.bookRepository.findAllByNameLikeAndCategory(nameLike,category);
        }
        else if(name != null)
        {
            return this.bookRepository.findAllByNameLike(nameLike);
        }
        else if(category != null)
        {
            return this.bookRepository.findAllByCategory(category);
        }
        else{
            return this.bookRepository.findAll();
        }

    }

    @Override
    public Book taken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        if(book.getAvailableCopies() > 0){
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        else{

        }
        bookRepository.save(book);
        return book;
    }
}
