package pl.spring.demo.mapper;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.entity.PersonalData;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.LibraryTo;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookTo map(BookEntity bookEntity) {
        if (bookEntity != null) {
        	List<AuthorTo> authorsTo = bookEntity.getAuthors().stream().map(BookMapper::map).collect(Collectors.toList());
            return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authorsTo, map(bookEntity.getLibrary()));
        }
        return null;
    }

    public static BookEntity map(BookTo bookTo) {
        if (bookTo != null) {
        	List<AuthorEntity> authorsEntity = bookTo.getAuthors().stream().map(BookMapper::map).collect(Collectors.toList());
            return new BookEntity(bookTo.getId(), bookTo.getTitle(), authorsEntity);
        }
        return null;
    }
    
    public static AuthorTo map(AuthorEntity authorEntity) {
    	if(authorEntity != null) {
    		PersonalData pd = authorEntity.getPersonalData();
    		return new AuthorTo(authorEntity.getId(), pd.getFirstName(), pd.getLastName());
    	}
    	return null;
    }
    
    public static AuthorEntity map(AuthorTo authorTo) {
    	System.out.println("Author: " + authorTo);
    	System.out.println("\tAuthor first name: " + authorTo.getFirstName());
    	System.out.println("\tAuthor last name: " + authorTo.getLastName());
    	if(authorTo != null) {
    		PersonalData pd = new PersonalData(authorTo.getFirstName(), authorTo.getLastName());
    		AuthorEntity ae = new AuthorEntity();
    		if(authorTo.getId() != null)
    			ae.setId(authorTo.getId());
    		ae.setPersonalData(pd);
    		return ae;
    	}
    	return null;
    }
    
    public static LibraryEntity map(LibraryTo libraryTo) {
    	if(libraryTo != null) {
    		return new LibraryEntity(libraryTo.getId(), libraryTo.getName(), null);
    	}
    	
    	return null;
    }

    public static LibraryTo map(LibraryEntity libraryEntity) {
    	if(libraryEntity != null) {
    		return new LibraryTo(libraryEntity.getId(), libraryEntity.getName());
    	}
    	
    	return null;
    }
    
    public static List<BookTo> map2To(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }
}
