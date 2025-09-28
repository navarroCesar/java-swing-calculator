package calculator.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Keyboard extends JPanel {

	private final Color COLOR_DARK_GRAY = new Color(68, 68, 68);
	private final Color COLOR_LIGHT_GRAY = new Color(99, 99, 99);
	private final Color COLOR_ORANGE = new Color(242, 163, 60);

	public Keyboard() {

		setLayout(new GridLayout(5, 4));

		add(new Button("AC", COLOR_DARK_GRAY));
		add(new Button("+/-", COLOR_DARK_GRAY));
		add(new Button("%", COLOR_DARK_GRAY));
		add(new Button("/", COLOR_ORANGE));

		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
	}
}
