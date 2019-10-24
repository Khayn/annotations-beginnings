package pl.khayn.annotations.abstracts;

public abstract class AbstractTypedService<T extends AbstractModel> {

	abstract public void callService(T data);
	
}
