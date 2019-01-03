package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.JpaConfig;
import ui.MainFrame;

public class MainApp {

	public static void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		MainApp.init();
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new MainFrame().setVisible(true);
	}
}
