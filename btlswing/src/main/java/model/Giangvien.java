package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the GIANGVIEN database table.
 * 
 */
@Entity
@Table(name = "GIANGVIEN")
@NamedQuery(name = "Giangvien.findAll", query = "SELECT g FROM Giangvien g")
public class Giangvien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "giangvien_ma")
	private int giangvienMa;

	@Column(name = "giangvien_sdt")
	private String giangvienSdt;

	@Column(name = "giangvien_ten")
	private String giangvienTen;

	// bi-directional many-to-one association to Khoahoc
	@OneToMany(mappedBy = "giangvienBean")
	private List<Khoahoc> khoahocs;

	public Giangvien() {
	}

	public int getGiangvienMa() {
		return this.giangvienMa;
	}

	public void setGiangvienMa(int giangvienMa) {
		this.giangvienMa = giangvienMa;
	}

	public String getGiangvienSdt() {
		return this.giangvienSdt;
	}

	public void setGiangvienSdt(String giangvienSdt) {
		this.giangvienSdt = giangvienSdt;
	}

	public String getGiangvienTen() {
		return this.giangvienTen;
	}

	public void setGiangvienTen(String giangvienTen) {
		this.giangvienTen = giangvienTen;
	}

	public List<Khoahoc> getKhoahocs() {
		return this.khoahocs;
	}

	public void setKhoahocs(List<Khoahoc> khoahocs) {
		this.khoahocs = khoahocs;
	}

	public Khoahoc addKhoahoc(Khoahoc khoahoc) {
		getKhoahocs().add(khoahoc);
		khoahoc.setGiangvienBean(this);

		return khoahoc;
	}

	public Khoahoc removeKhoahoc(Khoahoc khoahoc) {
		getKhoahocs().remove(khoahoc);
		khoahoc.setGiangvienBean(null);

		return khoahoc;
	}

}