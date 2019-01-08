package ui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainPanel mainPanel;
	public QuanLyHocVienPanel quanLyHocVienPanel;
	public QuanLyKhoaHocPanel quanLyKhoaHocPanel;
	public QuanLyGiangVienPanel quanLyGiaoVienPanel;

	public MainFrame() {
		init();
		addComponent();
	}

	public void init() {
		this.setSize(900, 700);
		this.setLayout(new CardLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("quan ly khoa hoc");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showOptionDialog(null, "ban co muon thoat khong", "xac nhan",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
						new String[] { "dong y", "khong" }, JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
	}

	public void addComponent() {
		mainPanel = new MainPanel();
		mainPanel.setFrame(this);

		quanLyHocVienPanel = new QuanLyHocVienPanel();
		quanLyHocVienPanel.setFrame(this);

		quanLyKhoaHocPanel = new QuanLyKhoaHocPanel();
		quanLyKhoaHocPanel.setFrame(this);
		
		quanLyGiaoVienPanel=new QuanLyGiangVienPanel();
		quanLyGiaoVienPanel.setFrame(this);

		this.add(mainPanel);
		this.add(quanLyHocVienPanel);
		this.add(quanLyKhoaHocPanel);
		this.add(quanLyGiaoVienPanel);
	}

}
