package pl.spring.demo.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entities.BookEntity;
import pl.spring.demo.helpers.AuthorToHelper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Component
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

    @Autowired
    private Sequence sequence;
    
    @Autowired
    private Converter<BookTo, BookEntity> bookTo2BookEntity;
    
    @Autowired
    private Converter<BookEntity, BookTo> bookEntity2BookTo;

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookTo> findAll() {
        return ALL_BOOKS.stream().map(bookEntity2BookTo::convert)
        		.collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
    	final String titleLower = title.toLowerCase();
    	return ALL_BOOKS.stream()
    			.filter(entity -> entity.getTitle().toLowerCase().startsWith(titleLower))
    			.map(bookEntity2BookTo::convert)
    			.collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	final AuthorTo authorTo = AuthorToHelper.string2Author(author);
    	return ALL_BOOKS.stream()
    			.filter(
    					entity -> entity.getAuthors().stream()
    					.filter(auth -> authorsEquals(authorTo, auth))
    					.count() > 0
    			)
    			.map(bookEntity2BookTo::convert)
    			.collect(Collectors.toList());
    }

    @Override
    @NullableId
    public BookTo save(BookTo book) {
        ALL_BOOKS.add(bookTo2BookEntity.convert(book));
        return book;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }
    
    public long getNextId() {
    	return sequence.nextValue(ALL_BOOKS);
    }
    
    private static boolean authorsEquals(AuthorTo author1, AuthorTo author2) {
    	return author1.getFirstName().equalsIgnoreCase(author2.getFirstName()) &&
    			author1.getLastName().equalsIgnoreCase(author2.getLastName());
    }

    private void addTestBooks() {
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
        ALL_BOOKS.add(new BookEntity(7L, "Przygody Olivera Twista", "Charles Dickens"));
        ALL_BOOKS.add(new BookEntity(8L, "Kreda na tablicy", "Julian Tuwim, Jan Parandowski, Marian Brandys, "
        		+ "Hanna Mortkowicz-Olczakowa, Gustaw Morcinek, Bohdan Czeszko, Mieczysław Jastrun, Stanisław Ryszard Dobrowolski, "
        		+ "Adam Grzymała-Siedlecki, Jerzy Zawieyski"));
    }
}
