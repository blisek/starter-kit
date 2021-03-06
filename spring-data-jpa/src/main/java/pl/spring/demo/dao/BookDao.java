package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entities.BookEntity;

public interface BookDao {

    List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);

    BookEntity save(BookEntity book);
}
