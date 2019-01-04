package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the HOCVIEN database table.
 * 
 */
@Entity
@Table(name = "HOCVIEN")
@NamedQuery(name = "Hocvien.findAll", query = "SELECT h FROM Hocvien h")
public class Hocvien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hocvien_ma")
	private int hocvienMa;

	@Column(name = "hocvien_sdt")
	private String hocvienSdt;

	@Column(name = "hocvien_ten")
	private Object hocvienTen;

	@Column(name = "hovien_tuoi")
	private int hovienTuoi;

	@OneToMany(mappedBy = "hocvien")
	private List<HocvienKhoahoc> hocvienKhoahocs;

	public Hocvien() {
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

	public Object getHocvienTen() {
		return this.hocvienTen;
	}

	public void setHocvienTen(Object hocvienTen) {
		this.hocvienTen = hocvienTen;
	}

	public int getHovienTuoi() {
		return this.hovienTuoi;
	}

	public void setHovienTuoi(int hovienTuoi) {
		this.hovienTuoi = hovienTuoi;
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