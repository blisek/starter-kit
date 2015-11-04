package pl.spring.demo.to;

import pl.spring.demo.helpers.AuthorToHelper;

public class AuthorTo {

	private int id;
	
	private String firstName;
	
	private String lastName;

	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AuthorTo(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AuthorTo() {}
	
	public static AuthorTo of(String author) {
		return AuthorToHelper.string2Author(author);
	}
}
