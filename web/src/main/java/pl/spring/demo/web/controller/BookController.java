package pl.spring.demo.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.controller.helpers.BookControllerHelper;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    
    private static final String BOOK_REDIRECT = "redirect:/books";
    private static final String BOOK_DELETED_MESSAGE = "Ksiazka \"%s\" autora %s zostala usunieta z bazy.";
    private static final String BOOK_ID_NOT_FOUND_MESSAGE = "Ksiazka o id %s nie zostala odnaleziona";

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
    
    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public String deleteBook(Map<String, Object> params, @RequestParam("bookId") Long bookId) {
    	BookTo bookTo = BookControllerHelper.findBookById(bookService, bookId.toString());
//    	if(bookTo != null) {
    		bookService.removeBook(bookId);
    		params.put(BookControllerHelper.MESSAGE_PARAM_MESSAGE, 
    				String.format(BOOK_DELETED_MESSAGE, bookTo.getTitle(), bookTo.getAuthors()));
//    	} else {
//    		params.put(BOOK_ID_NOT_FOUND_MESSAGE, bookId);
//    	}
    	params.put(BookControllerHelper.MESSAGE_PARAM_RETURN_LINK, "../books");
    	return "message";
    }
    
    @RequestMapping("/books/*")
    public String defaultRedirect(HttpServletRequest request) {
    	return BOOK_REDIRECT;
    }
}
