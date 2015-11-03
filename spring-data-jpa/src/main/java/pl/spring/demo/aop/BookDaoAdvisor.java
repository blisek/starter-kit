package pl.spring.demo.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@Aspect
@Component
public class BookDaoAdvisor {

	@Pointcut("within(pl.spring.demo.service.impl.BookServiceImpl) && @annotation(pl.spring.demo.annotation.NullableId)")
	public void nullableIdAnnotatedMethods() {}
	

	
	@Before("nullableIdAnnotatedMethods() && args(bookTo)")
	public void checkNullableId(BookTo bookTo) throws BookNotNullIdException {
		if(bookTo.getId() != null)
			throw new BookNotNullIdException();
		System.out.println("Id is null");
	}
	
	
}
