package mk.ukim.finki.demo.repository.jpa;

import mk.ukim.finki.demo.model.Book;
import mk.ukim.finki.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByName(String name);

    List<Book> findAllByNameLikeAndCategory(String name, Category category);

    List<Book> findAllByNameLike(String name);

    List<Book> findAllByCategory(Category category);
}
