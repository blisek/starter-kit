package pl.spring.demo.helpers;

import java.util.ArrayList;
import java.util.List;

import pl.spring.demo.to.AuthorTo;

public class AuthorToHelper {
	public static final String AUTHOR_DELIMITER = " ";
	public static final String AUTHORS_DELIMITER = ",";

	public static String author2String(AuthorTo author) {
		return String.join(" ", author.getFirstName(), author.getLastName());
	}
	
	public static AuthorTo string2Author(String author) {
		String[] authorNames = author.split(AUTHOR_DELIMITER);
		AuthorTo authorTo = new AuthorTo();
		if(authorNames.length == 1) {
			authorTo.setId(1);
			authorTo.setFirstName("");
			authorTo.setLastName(authorNames[0]);
		}
		else if(authorNames.length == 2) {
			authorTo.setId(1);
			authorTo.setFirstName(authorNames[0]);
			authorTo.setLastName(authorNames[1]);
		}
		else
			throw new IllegalArgumentException();
		
		return authorTo;
	}
	
	public static String authors2String(List<AuthorTo> authors) {
		int authorsLength = authors.size();
		String[] authorsStr = new String[authorsLength];
		for(int i = 0; i < authorsLength; ++i)
			authorsStr[i] = author2String(authors.get(i));
		return String.join(AUTHORS_DELIMITER, authorsStr);
	}
	
	public static List<AuthorTo> string2Authors(String authors) {
		List<AuthorTo> authorsList = new ArrayList<AuthorTo>();
		int idCounter = 1;
		for(String author : authors.split(AUTHORS_DELIMITER)) {
			AuthorTo authorTo = string2Author(author);
			authorTo.setId(idCounter++);
			authorsList.add(authorTo);
		}
		return authorsList;
	}
}
