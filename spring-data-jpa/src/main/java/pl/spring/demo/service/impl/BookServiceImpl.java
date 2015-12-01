package pl.spring.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QLibraryEntity;
import pl.spring.demo.helpers.BookSearchCriteria;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<BookTo> findAllBooks() {
        return BookMapper.map2To(bookRepository.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return BookMapper.map2To(bookRepository.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
//        return BookMapper.map2To(bookRepository.findBookByAuthor(author));
    	// TODO zmienić
    	return null;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void removeBook(Long bookId) {
    	bookRepository.delete(bookId);
    }

    @Override
    @Transactional(readOnly = false)
    public BookTo saveBook(BookTo book) {
    	System.out.println("Title: " + book.getTitle());
    	for(AuthorTo author : book.getAuthors()) {
    		System.out.println("author: " + author.getFirstName() + " " + author.getLastName());
    	}
        BookEntity entity = BookMapper.map(book);
        entity = bookRepository.save(entity);
        return BookMapper.map(entity);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateBook(BookTo book) {
    	BookEntity entity = BookMapper.map(book);
    	bookRepository.save(entity);
    }

	@Override
	public List<BookTo> findBooksBySearchCriteria(
			BookSearchCriteria searchCriteria) {
		final QBookEntity bookEntity = QBookEntity.bookEntity;
		final JPAQuery query = new JPAQuery(entityManager, HQLTemplates.DEFAULT).from(bookEntity);

		if (searchCriteria != null) {
			final BooleanBuilder predicate = new BooleanBuilder();

			if (!StringUtils.isEmpty(searchCriteria.getTitle())) {
				final String title = searchCriteria.getTitle();
				predicate.and(bookEntity.title.startsWithIgnoreCase(title));
			}
			if (!StringUtils.isEmpty(searchCriteria.getAuthor())) {
				final String[] author = searchCriteria.getAuthor().split(" ");
				if(author.length == 1) {
					predicate.and(bookEntity.authors.any().personalData.lastName.startsWithIgnoreCase(author[0]));
				} else if(author.length > 1) {
					predicate
						.and(bookEntity.authors.any().personalData.firstName.startsWithIgnoreCase(author[0])
							.and(bookEntity.authors.any().personalData.lastName.startsWithIgnoreCase(author[1]))
					);
				}
			}
			if (!StringUtils.isEmpty(searchCriteria.getLibraryName())) {
				QLibraryEntity libraryEntity = QLibraryEntity.libraryEntity;
				predicate.and(new JPASubQuery()
					.from(libraryEntity)
					.where(
							libraryEntity.books.any()
								.bookEntity.title.eq(bookEntity.title))
								.exists()
					);
			}
			query.where(predicate);
			return BookMapper.map2To(query.listResults(bookEntity).getResults());
		}
		return null;
	}
    
    

}
