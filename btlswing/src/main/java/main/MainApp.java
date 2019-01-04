package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.JpaConfig;
import dao.GiangVienDao;
import dao.HocVienDao;
import dao.HocVienKhoaHocDao;
import dao.KhoaHocDao;
import ui.MainFrame;

public class MainApp {

	public static GiangVienDao giangvienDao;
	public static HocVienDao hocvienDao;
	public static HocVienKhoaHocDao hocvien_khoahocDao;
	public static KhoaHocDao khoahocDao;

	@SuppressWarnings("resource")
	public static void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		MainApp.giangvienDao = context.getBean("giangvienDao", GiangVienDao.class);
		MainApp.hocvienDao = context.getBean("hocvienDao", HocVienDao.class);
		MainApp.hocvien_khoahocDao = context.getBean("hocvien_khoahocDao", HocVienKhoaHocDao.class);
		MainApp.khoahocDao = context.getBean("khoahocDao", KhoaHocDao.class);
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		MainApp.init();
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new MainFrame().setVisible(true);
	}
}
