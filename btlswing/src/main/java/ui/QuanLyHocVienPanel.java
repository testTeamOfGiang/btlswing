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
import model.HocVien;

public class QuanLyHocVienPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTable table;
	public DefaultTableModel tableModel;
	public Map<Integer, Integer> data;
	public int page;
	private JTextField ten;
	private JTextField sdt;
	private JTextField tuoi;

	@Override
	public void addComponent() {
		page = 0;
		data = new HashMap<Integer, Integer>();
		this.tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "tên học viên", "tuổi học viên", "sdt học viên", "khóa học tham gia" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.table = new JTable(tableModel);
		table.setRowHeight(40);
		table.setSize(900, 400);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int current = table.getSelectedRow();
					HocVien hv = MainApp.hocvienDao.findById(data.get(current)).get();
					ten.setText(hv.getHocvienTen());
					sdt.setText(hv.getHocvienSdt());
					tuoi.setText(hv.getHocvienTuoi() + "");
				}
				if (e.getClickCount() == 2) {
					System.out.println("hello");
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 900, 400);
		this.add(scrollPane);

		JButton btnPre = new JButton("pre");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPre.setBounds(245, 412, 117, 40);
		add(btnPre);

		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNext.setBounds(488, 412, 117, 40);
		add(btnNext);

		JButton btnBack = new JButton("quay lại");
		btnBack.setBounds(40, 463, 117, 40);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyHocVienPanel.setVisible(false);
				frame.mainPanel.setVisible(true);
			}
		});
		add(btnBack);

		JButton btnRefresh = new JButton("refresh");
		btnRefresh.setBounds(40, 514, 117, 40);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnRefresh);

		JButton search = new JButton("Tìm kiếm");
		search.setBounds(40, 570, 117, 40);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyHocVienPanel.setVisible(false);
				frame.timHocVienPanel.setVisible(true);
			}
		});
		this.add(search);

		JLabel lblTnGingVin = new JLabel("tên học viên");
		lblTnGingVin.setBounds(240, 484, 101, 35);
		add(lblTnGingVin);

		ten = new JTextField();
		ten.setBounds(359, 484, 246, 35);
		add(ten);
		ten.setColumns(10);

		JLabel lblSdtGingVin = new JLabel("sdt học viên");
		lblSdtGingVin.setBounds(241, 545, 100, 35);
		add(lblSdtGingVin);

		sdt = new JTextField();
		sdt.setBounds(359, 545, 246, 35);
		add(sdt);
		sdt.setColumns(10);

		JButton btnThm = new JButton("thêm");
		btnThm.setBounds(730, 463, 117, 40);
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = ten.getText();
					int age = Integer.parseInt(tuoi.getText());
					String phone = sdt.getText();
					HocVien hv = new HocVien();
					hv.setHocvienTen(name);
					hv.setHocvienTuoi(age);
					hv.setHocvienSdt(phone);
					if (name.trim().equals("") | phone.trim().equals("")) {
						throw new Exception();
					}
					MainApp.hocvienDao.save(hv);
					JOptionPane.showMessageDialog(frame, "them hoc vien thanh cong");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "them hoc vien khong thanh cong");
				}
				loadData();
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("sửa");
		btnSa.setBounds(730, 514, 117, 40);
		btnSa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(frame, "hãy chọn một bản ghi trước khi sửa");
				} else {
					try {
						int current = table.getSelectedRow();
						HocVien hv = MainApp.hocvienDao.findById(data.get(current)).get();
						MainApp.hocvienDao.delete(hv);
						String name = ten.getText();
						String phone = sdt.getText();
						int age = Integer.parseInt(sdt.getText());
						if (name.trim().equals("") || phone.trim().equals("")) {
							throw new Exception();
						}
						hv.setHocvienTen(name);
						hv.setHocvienSdt(phone);
						hv.setHocvienTuoi(age);
						MainApp.hocvienDao.save(hv);
						JOptionPane.showMessageDialog(frame, "sửa học viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "sửa học viên không thành công");
					}
				}
			}
		});
		add(btnSa);

		JButton btnXa = new JButton("xóa");
		btnXa.setBounds(730, 570, 117, 40);
		btnXa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(frame, "hãy chọn một bản ghi trước khi xóa");
				} else
					try {
						HocVien hv = MainApp.hocvienDao.findById(data.get(table.getSelectedRow())).get();
						MainApp.hocvienDao.delete(hv);
						JOptionPane.showMessageDialog(frame, "xóa thành công");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "xóa không thành công");
					}
				loadData();
			}
		});
		add(btnXa);

		JLabel lblNewLabel = new JLabel("tuổi học viên");
		lblNewLabel.setBounds(245, 603, 96, 40);
		add(lblNewLabel);

		tuoi = new JTextField();
		tuoi.setBounds(359, 604, 246, 35);
		add(tuoi);
		tuoi.setColumns(10);

		////////////////////////////////////
		loadData();
	}

	public void loadData() {
		if (data.size() > 0) {
			data.clear();
		}
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		List<HocVien> list = MainApp.hocvienDao.getPage(PageRequest.of(page, 50));
		int stt = 1;
		for (HocVien h : list) {
			String name = h.getHocvienTen();
			int age = h.getHocvienTuoi();
			String phone = h.getHocvienSdt();
			tableModel.addRow(new Object[] { stt, name, age, phone, h.getKhoahocs().size() });
			data.put(stt - 1, h.getHocvienMa());
			stt += 1;
		}
	}
}
