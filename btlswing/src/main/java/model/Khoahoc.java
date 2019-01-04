package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the KHOAHOC database table.
 * 
 */
@Entity
@Table(name = "KHOAHOC")
@NamedQuery(name = "Khoahoc.findAll", query = "SELECT k FROM Khoahoc k")
public class Khoahoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "khoahoc_ma")
	private int khoahocMa;

	@Column(name = "khoahoc_gia")
	private int khoahocGia;

	@Column(name = "khoahoc_ten")
	private Object khoahocTen;

	@OneToMany(mappedBy = "khoahoc")
	private List<HocvienKhoahoc> hocvienKhoahocs;

	@ManyToOne
	@JoinColumn(name = "giangvien")
	private Giangvien giangvienBean;

	public Khoahoc() {
	}

	public int getKhoahocMa() {
		return this.khoahocMa;
	}

	public void setKhoahocMa(int khoahocMa) {
		this.khoahocMa = khoahocMa;
	}

	public int getKhoahocGia() {
		return this.khoahocGia;
	}

	public void setKhoahocGia(int khoahocGia) {
		this.khoahocGia = khoahocGia;
	}

	public Object getKhoahocTen() {
		return this.khoahocTen;
	}

	public void setKhoahocTen(Object khoahocTen) {
		this.khoahocTen = khoahocTen;
	}

	public List<HocvienKhoahoc> getHocvienKhoahocs() {
		return this.hocvienKhoahocs;
	}

	public void setHocvienKhoahocs(List<HocvienKhoahoc> hocvienKhoahocs) {
		this.hocvienKhoahocs = hocvienKhoahocs;
	}

	public HocvienKhoahoc addHocvienKhoahoc(HocvienKhoahoc hocvienKhoahoc) {
		getHocvienKhoahocs().add(hocvienKhoahoc);
		hocvienKhoahoc.setKhoahoc(this);

		return hocvienKhoahoc;
	}

	public HocvienKhoahoc removeHocvienKhoahoc(HocvienKhoahoc hocvienKhoahoc) {
		getHocvienKhoahocs().remove(hocvienKhoahoc);
		hocvienKhoahoc.setKhoahoc(null);

		return hocvienKhoahoc;
	}

	public Giangvien getGiangvienBean() {
		return this.giangvienBean;
	}

	public void setGiangvienBean(Giangvien giangvienBean) {
		this.giangvienBean = giangvienBean;
	}

}