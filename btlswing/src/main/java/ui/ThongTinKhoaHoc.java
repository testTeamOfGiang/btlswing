package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.MainApp;
import model.HocVien;
import model.KhoaHoc;

public class ThongTinKhoaHoc extends AbstractJpanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public int khId;

	@Override
	public void addComponent() {
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Stt", "Mã học viên", "Tên học viên", "sdt" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.setBounds(0, 300, 900, 400);

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(0, 300, 900, 400);
		this.add(scrollpane);

		JLabel lblThngTinKha = new JLabel("Thông tin khóa học");
		lblThngTinKha.setBounds(135, 28, 109, 40);
		add(lblThngTinKha);

		JLabel lblThmHcVin = new JLabel("Thêm học viên");
		lblThmHcVin.setBounds(654, 28, 91, 40);
		add(lblThmHcVin);

		JLabel lblNewLabel = new JLabel("Mã khóa học");
		lblNewLabel.setBounds(60, 81, 84, 40);
		add(lblNewLabel);

		JLabel lblTnKhaHc = new JLabel("Tên khóa học");
		lblTnKhaHc.setBounds(60, 134, 84, 40);
		add(lblTnKhaHc);

		JLabel lblGingVin = new JLabel("Giảng viên");
		lblGingVin.setBounds(60, 187, 84, 40);
		add(lblGingVin);

		JLabel lblHcPh = new JLabel("Học phí");
		lblHcPh.setBounds(60, 240, 84, 40);
		add(lblHcPh);

		JLabel lblMHcVin = new JLabel("Mã học viên");
		lblMHcVin.setBounds(549, 81, 91, 40);
		add(lblMHcVin);

		textField = new JTextField();
		lblMHcVin.setLabelFor(textField);
		textField.setBounds(654, 81, 187, 40);
		add(textField);
		textField.setColumns(10);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(735, 134, 97, 40);
		btnThm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					HocVien hv = MainApp.hocvienDao.findById(Integer.parseInt(textField.getText())).get();
					KhoaHoc kh = MainApp.khoahocDao.findById(khId).get();
					hv.getKhoahocs().add(kh);
					MainApp.hocvienDao.save(hv);
					JOptionPane.showMessageDialog(frame, "Đăng ký học thành công");
					loadData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "Đăng ký học không thành công");
				}
			}
		});
		add(btnThm);

		JButton btnngHcPh = new JButton("refresh");
		btnngHcPh.setBounds(691, 216, 109, 40);
		btnngHcPh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnngHcPh);

		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.setBounds(549, 216, 109, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.thongTinKhoaHoc.setVisible(false);
				frame.quanLyKhoaHocPanel.setVisible(true);
			}
		});
		add(btnQuayLi);

		textField_1 = new JTextField();
		textField_1.setBounds(156, 81, 131, 40);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(156, 134, 131, 40);
		add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(156, 187, 131, 40);
		add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(156, 240, 131, 40);
		add(textField_4);
		textField_4.setColumns(10);

		/////////////////////////////
	}

	public void loadData() {
		int stt = 1;
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		KhoaHoc tam = MainApp.khoahocDao.findById(khId).get();
		textField_1.setText(tam.getKhoahocMa() + "");
		textField_2.setText(tam.getKhoahocTen());
		textField_3.setText(MainApp.khoahocDao.findById(khId).get().getGiangvienBean().getGiangvienTen());
		textField_4.setText(tam.getKhoahocGia() + "");

		List<HocVien> hvs = tam.getHocviens();
		for (HocVien hv : hvs) {
			tableModel.addRow(new Object[] { stt, hv.getHocvienMa(), hv.getHocvienTen(), hv.getHocvienSdt() });
			stt += 1;
		}

	}

}
