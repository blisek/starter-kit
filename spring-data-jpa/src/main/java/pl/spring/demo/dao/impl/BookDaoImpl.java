package pl.spring.demo.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.db.Storage;
import pl.spring.demo.entities.BookEntity;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private Storage<BookEntity> bookEntityStorage;
    

    @Override
    public List<BookEntity> findAll() {
        return bookEntityStorage.getAsAStream()
        		.collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
    	final String titleLower = title.toLowerCase();
    	return bookEntityStorage.getAsAStream()
    			.filter(entity -> entity.getTitle().toLowerCase().startsWith(titleLower))
    			.collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	final String authorsLowerCase = author.toLowerCase();
    	return bookEntityStorage.getAsAStream()
    			.filter(b -> b.getAuthors().toLowerCase().contains(authorsLowerCase))
    			.collect(Collectors.toList());
    }

    @Override
    public BookEntity save(BookEntity book) {
        bookEntityStorage.add(book);
        return book;
    }

    @PostConstruct
    private void addTestBooks() {
        bookEntityStorage.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
        bookEntityStorage.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
        bookEntityStorage.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
        bookEntityStorage.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        bookEntityStorage.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        bookEntityStorage.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
        bookEntityStorage.add(new BookEntity(7L, "Przygody Olivera Twista", "Charles Dickens"));
        bookEntityStorage.add(new BookEntity(8L, "Kreda na tablicy", "Julian Tuwim, Jan Parandowski, Marian Brandys, "
        		+ "Hanna Mortkowicz-Olczakowa, Gustaw Morcinek, Bohdan Czeszko, Mieczysław Jastrun, Stanisław Ryszard Dobrowolski, "
        		+ "Adam Grzymała-Siedlecki, Jerzy Zawieyski"));
    }
}
