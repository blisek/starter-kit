package pl.spring.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 2;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Druga książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "d";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Druga książka", booksEntity.get(0).getTitle());
    }
}
