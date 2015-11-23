package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.helpers.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindBookByAuthor() {
        // given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, "Jan Kowalski", null);
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals("Pierwsza książka", books.get(0).getTitle());
    }
    
    @Test
    public void testShouldNotFindBookByAuthorAndTitle() {
    	// given
    	// nie ten autor
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("Druga książka", "Jan Kowalski", null);
    	// when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
    	// then
    	assertNotNull(books);
    	assertTrue(books.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooks() {
    	// given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
    	// when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
    	List<BookTo> allBooks = bookService.findAllBooks();
    	// then
    	assertNotNull(books);
    	assertEquals(allBooks.size(), books.size());
    }
    
    @Test
    public void testShouldFindBookByAuthorAndTitle() {
        // given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("Pierwsza książka", "Jan Kowalski", null);
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByAuthorAndLibrary() {
        // given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, "Sebastian Bono", "Biblioteka Narodowa");
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByTitleAndLibrary() {
    	// given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("Druga książka", null, "Biblioteka Narodowa");
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByTitleAuthorLibrary() {
    	// given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("Druga książka", "Zbigniew Nowak", "Biblioteka Narodowa");
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void testShouldNotFindBookByTitleAuthorLibrary() {
    	// given
    	final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("Druga książka", "NIEISTNIEJACY", "Biblioteka Narodowa");
        // when
    	List<BookTo> books = bookService.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
