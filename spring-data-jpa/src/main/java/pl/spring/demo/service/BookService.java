package pl.spring.demo.service;

import pl.spring.demo.helpers.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {

    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);
    List<BookTo> findBooksBySearchCriteria(BookSearchCriteria searchCriteria);

    BookTo saveBook(BookTo book);
    void removeBook(Long id); 
    public void updateBook(BookTo book);
}
