package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.spring.demo.config.AppConfiguration;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class BookServiceImplTest {

	@Configuration
	@Import(AppConfiguration.class)
	static class ContextConfiguration { }

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(8, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksStartsWithPhrase() {
    	// given
    	final String title = "Przygody";
    	final Collection<String> expectedTitles = Arrays.asList("Przygody Odyseusza", "Przygody Olivera Twista");
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	List<String> titles = booksByTitle.stream().map(BookTo::getTitle).collect(Collectors.toList());   	
    	assertTrue(titles.containsAll(expectedTitles));
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
    
    @Test
    public void testShouldFindAllAuthors() {
    	// given
    	final String author = "Jan Parandowski";
    	
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	final List<String> expectedTitles = Arrays.asList("Przygody Odyseusza", "Kreda na tablicy");
    	List<String> titles = booksByAuthor.stream().map(BookTo::getTitle).collect(Collectors.toList());
    	assertTrue(titles.containsAll(expectedTitles));
    }
}
