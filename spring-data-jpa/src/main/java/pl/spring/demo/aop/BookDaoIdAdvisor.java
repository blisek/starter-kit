package pl.spring.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entities.BookEntity;

@Aspect
@Component
public class BookDaoIdAdvisor {
	
	@Autowired
	private Sequence bookStorageSequence;
	
	@Before("execution(* pl.spring.demo.dao.impl.BookDaoImpl.save(..)) && args(bookEntity)")
	public void assignNewId(JoinPoint joinPoint, BookEntity bookEntity) {
		bookEntity.setId(bookStorageSequence.nextValue(null)+1);
	}
	
}
