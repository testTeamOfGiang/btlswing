package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.MainApp;
import model.KhoaHoc;

public class TimKhoaHocPanel extends AbstractJpanel {
	private static final long serialVersionUID = 1L;

	public JTable table;
	public DefaultTableModel model;
	private JTextField textField;

	@Override
	public void addComponent() {
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "Mã khóa học", "Tên khóa học", "Giảng Viên" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table = new JTable(model);
		table.setRowHeight(40);
		table.setBounds(0, 200, 900, 500);
		JScrollPane jScrollPane = new JScrollPane(table);

		jScrollPane.setBounds(0, 200, 900, 500);
		this.add(jScrollPane);

		JLabel lblTn = new JLabel("Tên khóa học");
		lblTn.setBounds(328, 74, 127, 40);
		add(lblTn);

		textField = new JTextField();
		textField.setBounds(467, 74, 241, 40);
		add(textField);
		textField.setColumns(10);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setBounds(720, 74, 97, 40);
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				if (str.trim().equals("")) {
					while (table.getRowCount() > 0) {
						model.removeRow(0);
					}
				} else {
					loadData(str.trim());
				}
			}
		});
		add(btnTmKim);

		JButton btnQuayLi = new JButton("Quay  Lại");
		btnQuayLi.setBounds(75, 74, 97, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.timKhoaHocPanel.setVisible(false);
				frame.quanLyKhoaHocPanel.setVisible(true);
			}
		});
		add(btnQuayLi);
	}

	public void loadData(String str) {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
		List<KhoaHoc> khs = MainApp.khoahocDao.Search(str);
		System.out.println(khs.size());
		int stt = 1;
		for (KhoaHoc kh : khs) {
			model.addRow(new Object[] { stt, kh.getKhoahocMa(), kh.getKhoahocTen(),
					kh.getGiangvienBean().getGiangvienTen() });
			stt += 1;
		}
	}

}
