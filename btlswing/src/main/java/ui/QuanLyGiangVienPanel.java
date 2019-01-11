package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class QuanLyGiangVienPanel extends AbstractJpanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField ten;
	private JTextField sdt;
	public JTable table;
	public DefaultTableModel tableModel;
	public Map<Integer, Integer> data;
	public int page;
	private KeyListener KeyListener;

	@Override
	public void addComponent() {
		page = 0;
		data = new HashMap<Integer, Integer>();
		this.KeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sdt.requestFocus();
				}
				System.out.println(e.getSource().equals(sdt));
			}
		};
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
				if (e.getClickCount() == 1) {
					int current = table.getSelectedRow();
					GiangVien gv = MainApp.giangvienDao.findById(data.get(current)).get();
					ten.setText(gv.getGiangvienTen());
					sdt.setText(gv.getGiangvienSdt());
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
				if (page > 0) {
					page -= 1;
					loadData();
				} else {
					JOptionPane.showMessageDialog(frame, "đã là trang đầu tiên");
				}
			}
		});
		btnPre.setBounds(245, 412, 117, 40);
		add(btnPre);

		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long size = MainApp.giangvienDao.count();
				int pc = 0;
				int tam = (int) (size % 50);
				if (tam == 0) {
					pc = (int) (size / 50);
				} else {
					pc = (int) (size / 50 + 1);
				}
				if (page < pc) {
					page++;
					loadData();
				} else {
					JOptionPane.showMessageDialog(frame, "đã là trang cuối cùng");
				}
				btnPre.setEnabled(true);
			}
		});
		btnNext.setBounds(488, 412, 117, 40);
		add(btnNext);

		JButton btnBack = new JButton("quay lại");
		btnBack.setBounds(40, 463, 117, 40);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.quanLyGiaoVienPanel.setVisible(false);
				frame.mainPanel.setVisible(true);
			}
		});
		add(btnBack);

		JButton btnRefresh = new JButton("refresh");
		btnRefresh.setBounds(40, 555, 117, 40);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnRefresh);

		JLabel lblTnGingVin = new JLabel("tên giảng viên");
		lblTnGingVin.setBounds(240, 504, 101, 35);
		add(lblTnGingVin);

		ten = new JTextField();
		ten.setBounds(359, 504, 246, 35);
		ten.addKeyListener(KeyListener);
		add(ten);
		ten.setColumns(10);

		JLabel lblSdtGingVin = new JLabel("sdt giảng viên");
		lblSdtGingVin.setBounds(241, 565, 100, 35);
		add(lblSdtGingVin);

		sdt = new JTextField();
		sdt.setBounds(359, 565, 246, 35);
		add(sdt);
		sdt.setColumns(10);

		JButton btnThm = new JButton("thêm");
		btnThm.setBounds(730, 463, 117, 40);
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = ten.getText();
					String phone = sdt.getText();
					if (name.trim().equals("") || phone.trim().equals("")) {
						JOptionPane.showMessageDialog(frame, "hãy nhập đủ thông tin");
						throw new Exception();
					}
					GiangVien gv = new GiangVien();
					gv.setGiangvienTen(name);
					gv.setGiangvienSdt(phone);
					MainApp.giangvienDao.save(gv);
					JOptionPane.showMessageDialog(frame, "thêm giảng viên thành công");
					loadData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "thêm giảng viên không thành công");
				}
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("sửa");
		btnSa.setBounds(730, 514, 117, 40);
		btnSa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(frame, "chọn một bản ghi trước khi sửa");
				} else {
					try {
						int current = table.getSelectedRow();
						GiangVien gv = MainApp.giangvienDao.findById(data.get(current)).get();
						String name = ten.getText();
						String phone = sdt.getText();
						if (name.trim().equals("") || phone.trim().equals("")) {
							JOptionPane.showMessageDialog(frame, "Hãy nhập đủ thông tin");
							throw new Exception();
						}
						gv.setGiangvienTen(name);
						gv.setGiangvienSdt(phone);
						MainApp.giangvienDao.save(gv);
						JOptionPane.showMessageDialog(frame, "sửa giảng viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "Sửa giảng viên không thành công");
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
					JOptionPane.showMessageDialog(frame, "Hãy chọn một bản ghi trước khi xóa");
				} else {
					try {
						int current = table.getSelectedRow();
						GiangVien gv = MainApp.giangvienDao.findById(data.get(current)).get();
						MainApp.giangvienDao.delete(gv);
						JOptionPane.showMessageDialog(frame, "xóa giảng viên thành công");
						loadData();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "xóa giảng viên không thành công");
					}
				}
			}
		});
		add(btnXa);

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
		List<GiangVien> list = MainApp.giangvienDao.getPage(PageRequest.of(this.page, 50));
		System.out.println(list.size());
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
