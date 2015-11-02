package pl.spring.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@Aspect
public class BookDaoAdvisor {

	@Pointcut("within(pl.spring.demo..*) && @annotation(pl.spring.demo.annotation.NullableId)")
	public void nullableIdAnnotatedMethods() {}
	
	@Pointcut("execution(* pl.spring.demo.dao.impl.BookDaoImpl.save(..))")
	public void bookDaoSaveMethod() {}
	
	@Around("nullableIdAnnotatedMethods() && bookDaoSaveMethod() && args(bookTo)")
	public void checkNullIdAndAssignNewOne(JoinPoint joinPoint, BookTo bookTo) throws BookNotNullIdException {
		if(bookTo.getId() != null)
			throw new BookNotNullIdException();
		BookDaoImpl bookDao = (BookDaoImpl)joinPoint.getTarget();
		bookTo.setId(bookDao.getNextId());
	}
}
