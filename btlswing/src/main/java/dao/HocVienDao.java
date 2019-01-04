package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Hocvien;

@Repository("hocvienDao")
public interface HocVienDao extends JpaRepository<Hocvien, Integer> {

}
