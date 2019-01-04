package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.HocVien;

@Repository("hocvienDao")
public interface HocVienDao extends JpaRepository<HocVien, Integer> {

	@Query("select h from HocVien h join fetch h.hocvienKhoahocs where h.hocvienMa = :ma")
	public HocVien getHocVien(@Param("ma") int ma);
}
