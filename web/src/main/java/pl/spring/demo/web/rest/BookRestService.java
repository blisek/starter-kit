package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.controller.helpers.BookControllerHelper;

import java.util.List;

import javax.xml.ws.soap.Addressing;

@RestController
public class BookRestService {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
    public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
        return bookService.findBooksByTitle(titlePrefix);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public @ResponseBody BookTo saveBook(@RequestBody BookTo book) {
        return bookService.saveBook(book);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public void removeBook(@RequestBody Long bookId) {
    	bookService.removeBook(bookId);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public BookTo updateBook(@RequestBody BookTo book) {
    	bookService.updateBook(book);
    	return book;
    }
}
