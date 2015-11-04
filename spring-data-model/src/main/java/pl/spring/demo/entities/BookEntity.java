package pl.spring.demo.entities;

import pl.spring.demo.to.IdAware;

public class BookEntity implements IdAware {

    private Long id;
    private String title;
    private String authors;
    
    public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public BookEntity() {}
	
	public BookEntity(Long id, String title, String authors) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
