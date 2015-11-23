package pl.spring.demo.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private PersonalData personalData;
	
	@ManyToMany(mappedBy = "authors")
	private List<BookEntity> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	
	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

	public AuthorEntity() {}

	public AuthorEntity(long id, PersonalData personalData) {
		super();
		this.id = id;
		this.personalData = personalData;
	}
}
