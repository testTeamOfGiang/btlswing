package ui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	MainPanel p1;
	Panel2 p2;
	public MainFrame() {
		init();
		addComponent();
	}

	public void init() {
		this.setSize(900, 700);
		this.setLayout(new CardLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showOptionDialog(null, "ban co muon thoat khong", "xac nhan",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] { "co", "khong" },
						JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
	}

	public void addComponent() {
		 p1 = new MainPanel(1);
		 p2 = new Panel2();
		p1.setFrame(this);
		p2.setFrame(this);
		this.add(p1);
		this.add(p2);
	}
}
