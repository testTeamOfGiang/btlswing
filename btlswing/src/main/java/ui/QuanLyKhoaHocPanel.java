package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLyKhoaHocPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTable table;
	public DefaultTableModel tableModel;
	public JButton back;
	public JTextField ten, gia;

	@Override
	public void addComponent() {
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "tên khóa học", "học phí", "giáo viên" });
		table = new JTable(tableModel);
		table.setSize(900, 400);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 900, 400);

		this.back = new JButton("Quay lại");
		back.setBounds(50, 450, 150, 40);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyKhoaHocPanel.setVisible(false);
				frame.mainPanel.setVisible(true);
			}
		});

		///////////////////////
		this.add(scrollPane);
		this.add(back);
	}

}
