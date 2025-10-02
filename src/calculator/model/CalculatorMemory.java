package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMemory {

	private enum InputType {
		CLEAR, PLUS_MINUS, DIGIT, DIVISION, MULTIPLICATION, SUBTRACTION, SUM, EQUALS, DECIMAL_POINT;
	};

	private static final CalculatorMemory instance = new CalculatorMemory();

	private List<MemoryObserver> observers = new ArrayList<>();

	private InputType previousOperation = null;
	private boolean replace = false;
	private String currentText = "";
	private String bufferText = "";

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

		if (command == null) {
			return;
		} else if (command == InputType.CLEAR) {
			currentText = "";
			bufferText = "";
			replace = false;
			previousOperation = null;
		} else if (command == InputType.PLUS_MINUS && currentText.contains("-")) {
			currentText = currentText.substring(1);
		} else if (command == InputType.PLUS_MINUS && !currentText.contains("-")) {
			currentText = "-" + currentText;
		} else if (command == InputType.DIGIT || command == InputType.DECIMAL_POINT) {
			currentText = replace ? text : currentText + text;
			replace = false;
		} else {
			replace = true;
			currentText = getOperationResult();
			bufferText = currentText;
			previousOperation = command;

		}

		observers.forEach(o -> o.valueUpdated(getCurrentText()));
	}

	private String getOperationResult() {
		if (previousOperation == null || previousOperation == InputType.EQUALS) {
			return currentText;
		}

		double bufferNumber = Double.parseDouble(bufferText);
		double currentNumber = Double.parseDouble(currentText);

		double result = 0;
		if (previousOperation == InputType.SUM) {
			result = bufferNumber + currentNumber;
		} else if (previousOperation == InputType.SUBTRACTION) {
			result = bufferNumber - currentNumber;
		} else if (previousOperation == InputType.MULTIPLICATION) {
			result = bufferNumber * currentNumber;
		} else if (previousOperation == InputType.DIVISION) {
			result = bufferNumber / currentNumber;
		}

		String resultString = Double.toString(result);
		boolean isInteger = resultString.endsWith(".0");
		return isInteger ? resultString.replace(".0", "") : resultString;
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
			} else if ("±".equals(text)) {
				return InputType.PLUS_MINUS;
			} else if (".".equals(text) && !currentText.contains(".")) {
				return InputType.DECIMAL_POINT;
			}
		}
		return null;
	}

}
