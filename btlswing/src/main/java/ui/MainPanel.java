package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author huylam98it
 *
 */
public class MainPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton qlHocVien;
	JButton qlKhoaHoc;
	JButton qlGiaoVien;
	JLabel title;

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		this.title = new JLabel("Quản lý khóa học");
		title.setFont(new Font("Courier New", Font.BOLD, 32));
		title.setBounds(150, 70, 600, 40);

		this.qlHocVien = new JButton("QUẢN LÝ HỌC VIÊN");
		qlHocVien.setFont(new Font("Courier New", Font.BOLD, 20));
		qlHocVien.setBounds(300, 250, 300, 40);
		qlHocVien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.mainPanel.setVisible(false);
				frame.quanLyHocVienPanel.setVisible(true);
			}
		});

		this.qlKhoaHoc = new JButton("QUẢN LÝ KHÓA HỌC");
		qlKhoaHoc.setBounds(300, 350, 300, 40);
		qlKhoaHoc.setFont(new Font("Courier New", Font.BOLD, 20));
		qlKhoaHoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.mainPanel.setVisible(false);
				frame.quanLyKhoaHocPanel.setVisible(true);
			}
		});

		this.qlGiaoVien = new JButton("QUẢN LÝ GIẢNG VIÊN");
		qlGiaoVien.setBounds(300, 450, 300, 40);
		qlGiaoVien.setFont(new Font("Courier New", Font.BOLD, 20));
		qlGiaoVien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.mainPanel.setVisible(false);
				frame.quanLyGiaoVienPanel.setVisible(true);
			}
		});

		this.add(title);
		this.add(qlHocVien);
		this.add(qlKhoaHoc);
		this.add(qlGiaoVien);

	}

}
