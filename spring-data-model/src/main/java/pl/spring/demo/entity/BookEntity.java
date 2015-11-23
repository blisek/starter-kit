package pl.spring.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
@SuppressWarnings("serial")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    	name = "book_author", 
    	joinColumns = {@JoinColumn(name="book_id")},
    	inverseJoinColumns = {@JoinColumn(name="author_id")}
    )
    private List<AuthorEntity> authors;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "library_id")
    private LibraryEntity library;
    // for hibernate
    public BookEntity() {}

	public BookEntity(Long id, String title, List<AuthorEntity> authors) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
	}
	
	public BookEntity(Long id, String title, List<AuthorEntity> authors,
			LibraryEntity library) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.library = library;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}

	public LibraryEntity getLibrary() {
		return library;
	}

	public void setLibrary(LibraryEntity library) {
		this.library = library;
	}

	
}
