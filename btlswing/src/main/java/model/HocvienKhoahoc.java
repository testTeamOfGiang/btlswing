package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the HOCVIEN_KHOAHOC database table.
 * 
 */
@Entity
@Table(name = "HOCVIEN_KHOAHOC")
@NamedQuery(name = "HocvienKhoahoc.findAll", query = "SELECT h FROM HocvienKhoahoc h")
public class HocvienKhoahoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HocvienKhoahocPK id;

	private boolean dongtien;

	@ManyToOne
	@MapsId("hocvienMa")
	@JoinColumn(name = "hocvien_ma")
	private Hocvien hocvien;

	@ManyToOne
	@MapsId("khoahocMa")
	@JoinColumn(name = "khoahoc_ma")
	private Khoahoc khoahoc;

	public HocvienKhoahoc() {
	}

	public HocvienKhoahocPK getId() {
		return this.id;
	}

	public void setId(HocvienKhoahocPK id) {
		this.id = id;
	}

	public boolean getDongtien() {
		return this.dongtien;
	}

	public void setDongtien(boolean dongtien) {
		this.dongtien = dongtien;
	}

	public Hocvien getHocvien() {
		return this.hocvien;
	}

	public void setHocvien(Hocvien hocvien) {
		this.hocvien = hocvien;
	}

	public Khoahoc getKhoahoc() {
		return this.khoahoc;
	}

	public void setKhoahoc(Khoahoc khoahoc) {
		this.khoahoc = khoahoc;
	}

}