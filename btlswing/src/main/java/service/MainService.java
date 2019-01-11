package service;

import main.MainApp;
import model.HocVien;
import model.HocvienKhoahoc;
import model.KhoaHoc;

public class MainService {

	public boolean dangKyKhoaHoc(HocVien hv, KhoaHoc kh) {
		try {
			HocvienKhoahoc hvkh = new HocvienKhoahoc();
			hvkh.setHocvien(hv);
			hvkh.setKhoahoc(kh);
			MainApp.hocvien_khoahocDao.save(hvkh);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
