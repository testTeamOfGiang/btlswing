package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.data.domain.PageRequest;

import main.MainApp;
import model.GiangVien;
import model.KhoaHoc;

public class QuanLyKhoaHocPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTable table;
	public DefaultTableModel tableModel;
	public JButton back;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public Map<Integer, Integer> data;
	int page;

	@Override
	public void addComponent() {
		page = 0;
		data = new HashMap<Integer, Integer>();

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "tên khóa học", "học phí", "giảng viên" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setSize(900, 400);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int current = table.getSelectedRow();
				KhoaHoc kh = MainApp.khoahocDao.findById(data.get(current)).get();
				if (e.getClickCount() == 1) {
					textField.setText(kh.getKhoahocTen());
					textField_1.setText(kh.getKhoahocGia() + "");
					textField_2.setText(kh.getGiangvienBean().getGiangvienMa() + "");
				}
				if (e.getClickCount() == 2) {
					frame.quanLyKhoaHocPanel.setVisible(false);
					frame.thongTinKhoaHoc.khId = kh.getKhoahocMa();
					frame.thongTinKhoaHoc.setVisible(true);
					frame.thongTinKhoaHoc.loadData();
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 900, 400);

		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.setBounds(45, 472, 109, 40);
		btnQuayLi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyKhoaHocPanel.setVisible(false);
				frame.mainPanel.setVisible(true);
			}
		});
		add(btnQuayLi);

		JButton btnRefresh = new JButton("refresh");
		btnRefresh.setBounds(45, 544, 109, 40);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnRefresh);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.quanLyKhoaHocPanel.setVisible(false);
				frame.timKhoaHocPanel.setVisible(true);
			}
		});
		btnTmKim.setBounds(45, 616, 109, 40);
		add(btnTmKim);

		JLabel lblTnKhaHc = new JLabel("Tên khóa học");
		lblTnKhaHc.setBounds(264, 491, 77, 40);
		add(lblTnKhaHc);

		JLabel lblGiKhaHc = new JLabel("Giá khóa học");
		lblGiKhaHc.setBounds(264, 544, 77, 40);
		add(lblGiKhaHc);

		textField = new JTextField();
		textField.setBounds(352, 491, 241, 40);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(353, 544, 240, 40);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblGingVin = new JLabel("Mã giảng viên");
		lblGingVin.setBounds(264, 597, 77, 40);
		add(lblGingVin);

		textField_2 = new JTextField();
		textField_2.setBounds(352, 597, 241, 40);
		add(textField_2);
		textField_2.setColumns(10);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(734, 472, 116, 40);
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText();
					int gia = Integer.parseInt(textField_1.getText());
					int gv = Integer.parseInt(textField_2.getText());
					if (name.trim().equals("")) {
						throw new Exception();
					}
					GiangVien giangvien = MainApp.giangvienDao.findById(gv).get();
					KhoaHoc kh = new KhoaHoc();
					kh.setGiangvienBean(giangvien);
					kh.setKhoahocTen(name);
					kh.setKhoahocGia(gia);
					MainApp.khoahocDao.save(kh);
					JOptionPane.showMessageDialog(frame, "thêm khóa học thành công");
					loadData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "thêm khóa học không thành công");
				}
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(734, 544, 116, 40);
		btnSa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					JOptionPane.showMessageDialog(frame, "Hãy chọn một bản ghi trước khi sửa");
				} else {
					try {
						KhoaHoc kh = MainApp.khoahocDao.findById(data.get(current)).get();
						String name = textField.getText();
						int price = Integer.parseInt(textField_1.getText());
						int gv = Integer.parseInt(textField_2.getText());
						kh.setKhoahocTen(name);
						kh.setKhoahocGia(price);
						kh.setGiangvienBean(MainApp.giangvienDao.findById(gv).get());
						MainApp.khoahocDao.save(kh);
						JOptionPane.showMessageDialog(frame, "Sửa giảng viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "Sửa giảng viên không thành công");
					}
				}
			}
		});
		add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(734, 616, 116, 40);
		btnXa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					JOptionPane.showMessageDialog(frame, "Hãy chọn một bản ghi trước khi xóa");
				} else {
					try {
						KhoaHoc kh = MainApp.khoahocDao.findById(data.get(current)).get();
						MainApp.khoahocDao.delete(kh);
						JOptionPane.showMessageDialog(frame, "Xóa giảng viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "Xóa giảng viên không thành công");
					}
				}
			}
		});
		add(btnXa);

		JButton btnTrc = new JButton("Trước");
		btnTrc.setBounds(231, 428, 97, 40);
		add(btnTrc);

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(517, 428, 97, 40);
		add(btnSau);

		///////////////////////
		this.add(scrollPane);

		loadData();
	}

	public void loadData() {
		int stt = 1;
		data.clear();
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		List<KhoaHoc> list = MainApp.khoahocDao.getPage(PageRequest.of(page, 50));
		for (KhoaHoc kh : list) {
			tableModel.addRow(new Object[] { stt, kh.getKhoahocTen(), kh.getKhoahocGia(),
					kh.getGiangvienBean().getGiangvienTen() });
			data.put(stt - 1, kh.getKhoahocMa());
			stt += 1;
		}
	}
}
