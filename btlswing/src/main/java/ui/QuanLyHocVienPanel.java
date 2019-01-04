package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.MainApp;
import model.HocVien;

public class QuanLyHocVienPanel extends AbstractJpanel {

	private static final long serialVersionUID = 1L;

	public JTable table;
	public DefaultTableModel tableModel;
	public JButton back, add, delete, refresh;
	public JTextField ten, tuoi, sdt;
	public Map<Integer, Integer> data;

	@Override
	public void addComponent() {
		data = new HashMap<Integer, Integer>();
		// TODO Auto-generated method stub
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
				if (e.getClickCount() == 2)
					System.out.println("hello");
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 900, 400);

		this.back = new JButton("Quay lại");
		back.setBounds(50, 450, 150, 40);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyHocVienPanel.setVisible(false);
				frame.mainPanel.setVisible(true);
			}
		});

		this.refresh = new JButton("refresh");
		refresh.setBounds(50, 550, 150, 40);
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});

		this.add = new JButton("thêm");
		add.setBounds(700, 450, 150, 40);
		add.addActionListener(new ActionListener() {
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

		this.delete = new JButton("xóa");
		delete.setBounds(700, 550, 150, 40);
		delete.addActionListener(new ActionListener() {
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

		this.ten = new JTextField();
		ten.setBounds(350, 440, 200, 40);

		this.tuoi = new JTextField();
		tuoi.setBounds(350, 500, 200, 40);

		this.sdt = new JTextField();
		sdt.setBounds(350, 560, 200, 40);

		//////////////
		this.add(scrollPane);
		this.add(back);
		this.add(ten);
		this.add(tuoi);
		this.add(sdt);
		this.add(add);
		this.add(delete);
		this.add(refresh);

		loadData();
	}

	public void loadData() {
		if (data.size() > 0) {
			data.clear();
		}
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		List<HocVien> list = MainApp.hocvienDao.findAll();
		int stt = 1;
		for (HocVien h : list) {
			String name = h.getHocvienTen();
			int age = h.getHocvienTuoi();
			String phone = h.getHocvienSdt();
			HocVien tam = MainApp.hocvienDao.getHocVien(h.getHocvienMa());
			int sl = 0;
			if (tam != null) {
				sl = tam.getHocvienKhoahocs().size();
			}
			tableModel.addRow(new Object[] { stt, name, age, phone, sl });
			data.put(stt - 1, h.getHocvienMa());
			stt += 1;
		}
	}

}
