package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entities.BookEntity;

@Aspect
@Component
public class BookDaoIdAdvisor {
	
	@Before("execution(* pl.spring.demo.dao.impl.BookDaoImpl.save(..)) && args(bookEntity)")
	public void assignNewId(JoinPoint joinPoint, BookEntity bookEntity) {
		BookDaoImpl bookDao = (BookDaoImpl)joinPoint.getTarget();
		bookEntity.setId(bookDao.getNextId());
	}
	
}
