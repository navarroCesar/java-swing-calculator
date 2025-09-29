package calculator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calculator.model.CalculatorMemory;
import calculator.model.MemoryObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoryObserver {

	private final JLabel label;

	public Display() {
		CalculatorMemory.getInstance().addObserver(this);

		setBackground(new Color(46, 49, 50));
		label = new JLabel(CalculatorMemory.getInstance().getCurrentText());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 26));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		add(label);
	}

	@Override
	public void valueUpdated(String newValue) {
		label.setText(newValue);
	}
}
