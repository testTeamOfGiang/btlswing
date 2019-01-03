package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Panel2 extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void addComponent() {
		JButton b = new JButton("panel 2");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.p2.setVisible(false);
				frame.p1.setVisible(true);

			}
		});
		b.setBounds(100, 100, 100, 40);
		this.add(b);
	}

}
