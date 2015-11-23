package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindBooksByTitle() {
        // given
    	final String libraryPrefix = "Roosevelt";
        // when
        List<LibraryEntity> booksEntity = libraryRepository.findLibraryByName(libraryPrefix);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Roosevelt National Library", booksEntity.get(0).getName());
    }
    
    @Test
    public void testShouldRemoveAllBooksWithLibrary() {
    	// given
    	final long libraryId = 1;
    	// when
    	LibraryEntity libraryEntity = libraryRepository.findOne(libraryId);
    	assertNotNull(libraryEntity);
    	assertFalse(libraryEntity.getBooks().isEmpty());
    	int libraryBooksCount = libraryEntity.getBooks().size();
    	Long bookCountBefore = bookRepository.count();
    	
    	libraryRepository.delete(libraryEntity);
    	
    	assertEquals(bookCountBefore - libraryBooksCount, bookRepository.count());
    }
}
