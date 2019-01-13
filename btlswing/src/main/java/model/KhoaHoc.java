package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the KHOAHOC database table.
 * 
 */
@Entity
@Table(name = "KHOAHOC")
public class KhoaHoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "khoahoc_ma")
	private int khoahocMa;

	@Column(name = "khoahoc_gia")
	private int khoahocGia;

	@Column(name = "khoahoc_ten")
	private String khoahocTen;

	@OneToMany(mappedBy = "khoahoc")
	private List<HocvienKhoahoc> hocvienKhoahocs=new ArrayList<HocvienKhoahoc>();

	@ManyToOne
	@JoinColumn(name = "giangvien")
	private GiangVien giangvienBean;

	public KhoaHoc() {
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

	public String getKhoahocTen() {
		return this.khoahocTen;
	}

	public void setKhoahocTen(String khoahocTen) {
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

	public GiangVien getGiangvienBean() {
		return this.giangvienBean;
	}

	public void setGiangvienBean(GiangVien giangvienBean) {
		this.giangvienBean = giangvienBean;
	}

}