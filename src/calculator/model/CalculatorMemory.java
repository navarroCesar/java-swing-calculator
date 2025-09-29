package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMemory {

	private static final CalculatorMemory instance = new CalculatorMemory();

	private List<MemoryObserver> observers = new ArrayList<>();

	private String currentText = "";

	private CalculatorMemory() {

	}

	public static CalculatorMemory getInstance() {
		return instance;
	}

	public void addObserver(MemoryObserver o) {
		observers.add(o);
	}

	public String getCurrentText() {
		return currentText.isEmpty() ? "0" : currentText;
	}

	public void handleCommand(String value) {
		if ("AC".equals(value)) {
			currentText = "";
		} else {
			currentText += value;
		}
		observers.forEach(o -> o.valueUpdated(getCurrentText()));
	}

}
