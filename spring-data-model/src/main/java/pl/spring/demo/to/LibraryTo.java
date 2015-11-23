package pl.spring.demo.to;

public class LibraryTo {

	private Long id;
	
	private String name;


	public LibraryTo() {}
	
	public LibraryTo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
