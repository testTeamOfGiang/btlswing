package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.GiangVien;

@Repository("giangvienDao")
public interface GiangVienDao extends JpaRepository<GiangVien, Integer> {

	@Query("select g from GiangVien g join fetch g.khoahocs where g.giangvienMa = :id")
	public GiangVien getGiangVien(@Param("id") int id);
}
