package pl.spring.demo.web.controller.helpers;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

public class BookControllerHelper {
	public static final String MESSAGE_PARAM_MESSAGE = "message";
	public static final String MESSAGE_PARAM_RETURN_LINK = "return_link";

	public static BookTo findBookById(BookService service, String id) {
    	Long bookId = Long.parseLong(id);
    	
    	for(BookTo bookTo : service.findAllBooks())
    		if(bookId.equals(bookTo.getId()))
    			return bookTo;
    	
    	return null;
	}
}
