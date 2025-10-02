package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMemory {

	private enum InputType {
		CLEAR, DIGIT, DIVISION, MULTIPLICATION, SUBTRACTION, SUM, EQUALS, DECIMAL_POINT;
	};

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

	public void handleCommand(String text) {
		InputType command = identifyInputType(text);
		System.out.println(command);
		if ("AC".equals(text)) {
			currentText = "";
		} else {
			currentText += text;
		}
		observers.forEach(o -> o.valueUpdated(getCurrentText()));
	}

	private InputType identifyInputType(String text) {
		if (currentText.isEmpty() && text == "0") {
			return null;
		}

		try {
			Integer.parseInt(text);
			return InputType.DIGIT;
		} catch (NumberFormatException e) {
			// When it is not a digit:
			if ("AC".equals(text)) {
				return InputType.CLEAR;
			} else if ("/".equals(text)) {
				return InputType.DIVISION;
			} else if ("*".equals(text)) {
				return InputType.MULTIPLICATION;
			} else if ("+".equals(text)) {
				return InputType.SUM;
			} else if ("-".equals(text)) {
				return InputType.SUBTRACTION;
			} else if ("=".equals(text)) {
				return InputType.EQUALS;
			} else if (".".equals(text)) {
				return InputType.DECIMAL_POINT;
			}
		}
		return null;
	}

}
