package pl.spring.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;

@Controller
@RequestMapping(value = "/authors")
public class AuthorRestService {

	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public @ResponseBody List<AuthorTo> getAllAuthors() {
		return authorService.findAllAuthors();
	}
}