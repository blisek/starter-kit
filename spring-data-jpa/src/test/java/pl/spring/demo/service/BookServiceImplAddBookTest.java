package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

import pl.spring.demo.config.AppConfiguration;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class BookServiceImplAddBookTest {

	@Configuration
	@Import(AppConfiguration.class)
	static class ContextConfiguration {}
	
	@Autowired
	public BookService bookService;
	
    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        // when
        BookTo result = bookService.saveBook(book);
        // then
        assertNotNull(result);
        assertEquals(9L, result.getId().longValue());
    }
}
