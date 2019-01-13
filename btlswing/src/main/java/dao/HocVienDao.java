package dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.HocVien;

@Repository("hocvienDao")
public interface HocVienDao extends JpaRepository<HocVien, Integer> {

	@Query("select h from HocVien h join fetch h.khoahocs where h.hocvienMa = :ma")
	public HocVien getHocVien(@Param("ma") int ma);

	@Query("select h from HocVien h")
	public List<HocVien> getPage(Pageable page);
}
