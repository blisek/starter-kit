package pl.spring.demo.dao;

import java.util.List;

public interface Dao<T> {

	public T save(T t);
	
	public List<T> findAll();
	
}
