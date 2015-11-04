package pl.spring.demo.common;

import java.util.Collection;

import pl.spring.demo.db.Storage;
import pl.spring.demo.to.IdAware;

public class StorageSequence<T extends IdAware> extends Sequence {

	private Storage<T> storage;
	
	public StorageSequence(Storage<T> storage) {
		this.storage = storage;
	}

	@Override
	public long nextValue(Collection<? extends IdAware> notUsed) {
		return super.nextValue(storage.getAll());
	}
	
	
}
