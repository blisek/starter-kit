package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        Mockito.reset(bookService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Ignore
    public void testShouldCallBookService() throws Exception {
        // given
        final String bookTitle = "testTitle";

        final BookTo bookTo1 = new BookTo(1L, bookTitle, Arrays.asList(new AuthorTo(1L, "Author", "Num. 1")));
        final BookTo bookTo2 = new BookTo(2L, bookTitle, Arrays.asList(new AuthorTo(2L, "Author", "Num. 2")));
        
        Mockito.when(bookService.findBooksByTitle(bookTitle)).thenReturn(Arrays.asList(bookTo1, bookTo2));

        // when
        ResultActions response = this.mockMvc.perform(get("/books/books-by-title?titlePrefix=" + bookTitle)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        Mockito.verify(bookService).findBooksByTitle(bookTitle);

        response.andExpect(status().isOk())

                .andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
                .andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
                .andExpect(jsonPath("[0].authors").isArray())
                
                .andExpect(jsonPath("[1].id").value(bookTo2.getId().intValue()))
                .andExpect(jsonPath("[1].title").value(bookTo2.getTitle()))
                .andExpect(jsonPath("[1].authors").isArray());
    }

    @Test
    public void testShouldSaveBook() throws Exception {
        // given
        File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/bookToSave.json");
        String json = FileUtils.readFileToString(file);
        // when
        ResultActions response = this.mockMvc.perform(post("/books/book")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()));
        // then
        response.andExpect(status().isOk());
    }
    
    @Test
    public void testShouldRemoveBook() throws Exception {
    	// given
    	final Long removedBookId = 3l;
    	// when
    	ResultActions response = this.mockMvc.perform(delete("/books/book")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(removedBookId.toString()));
    	response.andExpect(status().isOk());
    	Mockito.verify(bookService).removeBook(removedBookId);
    }
}
