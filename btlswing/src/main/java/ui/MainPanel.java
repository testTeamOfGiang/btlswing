package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainPanel extends AbstractJpanel {

	private static final long serialVersionUID = 1L;

	public MainPanel(int a) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		JButton b = new JButton("button panel1");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.p1.setVisible(false);
				frame.p2.setVisible(true);
			}
		});
		b.setBounds(100, 100, 100, 40);
		this.add(b);
	}

}
