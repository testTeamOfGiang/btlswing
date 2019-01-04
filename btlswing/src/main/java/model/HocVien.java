package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the HOCVIEN database table.
 * 
 */
@Entity
@Table(name = "HOCVIEN")
public class HocVien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hocvien_ma")
	private int hocvienMa;

	@Column(name = "hocvien_sdt")
	private String hocvienSdt;

	@Column(name = "hocvien_ten")
	private String hocvienTen;

	@Column(name = "hovien_tuoi")
	private int hocvienTuoi;

	@OneToMany(mappedBy = "hocvien")
	private List<HocvienKhoahoc> hocvienKhoahocs = new ArrayList<HocvienKhoahoc>();

	public HocVien() {
	}

	public int getHocvienMa() {
		return this.hocvienMa;
	}

	public void setHocvienMa(int hocvienMa) {
		this.hocvienMa = hocvienMa;
	}

	public String getHocvienSdt() {
		return this.hocvienSdt;
	}

	public void setHocvienSdt(String hocvienSdt) {
		this.hocvienSdt = hocvienSdt;
	}

	public String getHocvienTen() {
		return this.hocvienTen;
	}

	public void setHocvienTen(String hocvienTen) {
		this.hocvienTen = hocvienTen;
	}

	public int getHocvienTuoi() {
		return this.hocvienTuoi;
	}

	public void setHocvienTuoi(int hovienTuoi) {
		this.hocvienTuoi = hovienTuoi;
	}

	public List<HocvienKhoahoc> getHocvienKhoahocs() {
		return this.hocvienKhoahocs;
	}

	public void setHocvienKhoahocs(List<HocvienKhoahoc> hocvienKhoahocs) {
		this.hocvienKhoahocs = hocvienKhoahocs;
	}

	public HocvienKhoahoc addHocvienKhoahoc(HocvienKhoahoc hocvienKhoahoc) {
		getHocvienKhoahocs().add(hocvienKhoahoc);
		hocvienKhoahoc.setHocvien(this);

		return hocvienKhoahoc;
	}

	public HocvienKhoahoc removeHocvienKhoahoc(HocvienKhoahoc hocvienKhoahoc) {
		getHocvienKhoahocs().remove(hocvienKhoahoc);
		hocvienKhoahoc.setHocvien(null);

		return hocvienKhoahoc;
	}

}