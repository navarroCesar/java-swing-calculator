package calculator.model;

@FunctionalInterface
public interface MemoryObserver {

	void valueUpdated(String newValue);	
}
