package pl.spring.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {

    @Autowired
    private LibraryDao libraryDao;

    @Test
    public void testShouldFindLibrary() {
        // given
        final String libraryPrefix = "Biblioteka N";
        // when
        List<LibraryEntity> libraryEntities = libraryDao.findLibraryByName(libraryPrefix);
        // then
        assertNotNull(libraryEntities);
        assertEquals(1, libraryEntities.size());
        assertEquals("Biblioteka Narodowa", libraryEntities.get(0).getName());
    }

}
