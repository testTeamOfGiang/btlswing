package ui;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * @author huylam98it
 *
 */
public abstract class AbstractJpanel extends JPanel implements UIComponent {
	private static final long serialVersionUID = 1L;
	protected MainFrame frame;

	public AbstractJpanel() {
		this.init();
		this.addComponent();
	}

	public void init() {
		this.setSize(900, 700);
		this.setBackground(Color.gray);
		this.setLayout(null);
	}

	public void setFrame(MainFrame f) {
		this.frame = f;
	}

}
