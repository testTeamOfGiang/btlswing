package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.GiangVien;

@Repository("giangvienDao")
public interface GiangVienDao extends JpaRepository<GiangVien, Integer> {

}
