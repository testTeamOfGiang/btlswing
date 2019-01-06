package ui;

import java.awt.Font;
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

import main.MainApp;
import model.GiangVien;

public class QuanLyGiaoVienPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTable table;
	public JLabel lbname, lbsdt;
	public DefaultTableModel tableModel;
	public JButton back, add, delete, refresh, sua, joint;
	public JTextField ten, tuoi, sdt;
	public Map<Integer, Integer> data;

	@Override
	public void addComponent() {
		data = new HashMap<Integer, Integer>();
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "tên giảng viên", "sdt giảng viên", "số lớp dạy" }) {
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

		this.ten = new JTextField();
		ten.setBounds(400, 450, 150, 40);

		this.sdt = new JTextField();
		sdt.setBounds(400, 550, 150, 40);

		this.lbname = new JLabel("tên", JLabel.RIGHT);
		lbname.setFont(new Font("", Font.CENTER_BASELINE, 15));
		lbname.setBounds(250, 450, 100, 40);

		this.lbsdt = new JLabel("số đt", JLabel.RIGHT);
		lbsdt.setFont(new Font("", Font.CENTER_BASELINE, 15));
		lbsdt.setBounds(250, 550, 100, 40);

		this.back = new JButton("Quay lại");
		back.setBounds(50, 450, 150, 40);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyGiaoVienPanel.setVisible(false);
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

		this.add = new JButton("Thêm giảng viên");
		add.setBounds(700, 500, 150, 40);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String gvname = ten.getText();
					String gvsdt = sdt.getText();
					GiangVien gv = new GiangVien();
					gv.setGiangvienTen(gvname);
					gv.setGiangvienSdt(gvsdt);
					if (gvname.trim().equals("") || gvsdt.trim().equals("")) {
						throw new Exception();
					}
					MainApp.giangvienDao.save(gv);
					JOptionPane.showMessageDialog(frame, "thêm giảng viên thành công");
					loadData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "thêm giảng viên không thành công");
				}
			}
		});

		this.delete = new JButton("Xóa giảng viên");
		delete.setBounds(700, 550, 150, 40);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(frame, "chọn một bản ghi trước khi xóa");
				} else {
					try {
						int row = table.getSelectedRow();
						GiangVien gv = MainApp.giangvienDao.findById(data.get(row)).get();
						MainApp.giangvienDao.delete(gv);
						JOptionPane.showMessageDialog(frame, "xóa giảng viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "xóa giảng viên không thành công");
					}
				}
			}
		});

		////////////////////////

		this.add(scrollPane);
		this.add(ten);
		this.add(sdt);
		this.add(lbname);
		this.add(lbsdt);
		this.add(back);
		this.add(refresh);
		this.add(add);
		this.add(delete);
		
		loadData();
	}

	public void loadData() {
		if (this.data.size() > 0) {
			this.data.clear();
		}
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		List<GiangVien> list = MainApp.giangvienDao.findAll();
		for (GiangVien gv : list) {
			String gvten = gv.getGiangvienTen();
			String gvsdt = gv.getGiangvienSdt();
			int solop = 0;
			GiangVien tam = MainApp.giangvienDao.getGiangVien(gv.getGiangvienMa());
			if (tam != null) {
				solop = tam.getKhoahocs().size();
			}
			tableModel.addRow(new Object[] { stt, gvten, gvsdt, solop });
			data.put(stt - 1, gv.getGiangvienMa());
			stt += 1;
		}
	}
}
