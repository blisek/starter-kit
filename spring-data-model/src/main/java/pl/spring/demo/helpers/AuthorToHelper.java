package pl.spring.demo.helpers;

import java.util.ArrayList;
import java.util.Arrays;
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
			authorTo.setLastName(authorNames[0].trim());
		}
		else {
			String lastName = authorNames[authorNames.length-1];
			String names = String.join(AUTHOR_DELIMITER, Arrays.copyOf(authorNames, authorNames.length-1));
			authorTo.setId(1);
			authorTo.setFirstName(names);
			authorTo.setLastName(lastName);
		}
		
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
			AuthorTo authorTo = string2Author(author.trim());
			authorTo.setId(idCounter++);
			authorsList.add(authorTo);
		}
		return authorsList;
	}
}
