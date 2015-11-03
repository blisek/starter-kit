package pl.spring.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entities.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private Converter<BookTo, BookEntity> bookTo2BookEntity;
    
    @Autowired
    private Converter<BookEntity, BookTo> bookEntity2BookTo;

	@Autowired
    private BookDao bookDao;

    @Override
    public List<BookTo> findAllBooks() {
    	return bookDao.findAll()
    			.stream()
    			.map(bookEntity2BookTo::convert)
        		.collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	return bookDao.findBookByTitle(title)
    			.stream()
    			.map(bookEntity2BookTo::convert)
    			.collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	return bookDao.findBooksByAuthor(author)
    			.stream()
    			.map(bookEntity2BookTo::convert)
    			.collect(Collectors.toList());
    }

    @Override
    @NullableId
    public BookTo saveBook(BookTo book) {
        BookEntity be = bookDao.save(bookTo2BookEntity.convert(book));
        return bookEntity2BookTo.convert(be);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
