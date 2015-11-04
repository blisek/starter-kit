package pl.spring.demo.db;

import java.util.Collection;
import java.util.stream.Stream;

public class Storage<T> {
	
	public Storage(Class<? extends Collection<T>> collectionClass) throws InstantiationException, IllegalAccessException {
		collection = collectionClass.newInstance();
	}
	
	private Collection<T> collection;
	
	public boolean add(T t) {
		return collection.add(t);
	}
	
	public Collection<T> getAll() {
		return collection;
	}
	
	public Stream<T> getAsAStream() {
		return collection.stream();
	}
	
	public boolean contains(T t) {
		return collection.contains(t);
	}
	
	public boolean remove(T t) {
		return collection.remove(t);
	}
}
