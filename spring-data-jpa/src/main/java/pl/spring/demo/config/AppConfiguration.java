package pl.spring.demo.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.common.StorageSequence;
import pl.spring.demo.db.Storage;
import pl.spring.demo.entities.BookEntity;
import pl.spring.demo.helpers.AuthorToHelper;
import pl.spring.demo.to.BookTo;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.spring.demo")
@PropertySource("classpath:config/application.properties")
public class AppConfiguration {

	@Bean
	public Sequence sequence() {
		return new Sequence();
	}
	
	@Bean
	public Sequence bookStorageSequence() {
		return new StorageSequence<BookEntity>(bookEntityStorage());
	}
	
	@Bean
	public Storage<BookEntity> bookEntityStorage() {
		try {
			@SuppressWarnings("unchecked")
			Storage<BookEntity> storage = new Storage<BookEntity>((Class<? extends Collection<BookEntity>>) HashSet.class);
			return storage;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Bean
	public Converter<BookTo, BookEntity> bookTo2BookEntity() {
		return new Converter<BookTo, BookEntity>() {
			
			@Override
			public BookEntity convert(BookTo arg0) {
				BookEntity be = new BookEntity();
				be.setId(arg0.getId());
				be.setTitle(arg0.getTitle());
				be.setAuthors(AuthorToHelper.authors2String(arg0.getAuthors()));
				return be;
			}
		};
	}
	
	@Bean
	public Converter<BookEntity, BookTo> bookEntity2BookTo() {
		return new Converter<BookEntity, BookTo>() {
			
			@Override
			public BookTo convert(BookEntity arg0) {
				BookTo bookTo = new BookTo();
				bookTo.setId(arg0.getId());
				bookTo.setTitle(arg0.getTitle());
				bookTo.setAuthors(AuthorToHelper.string2Authors(arg0.getAuthors()));
				return bookTo;
			}
		};
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
