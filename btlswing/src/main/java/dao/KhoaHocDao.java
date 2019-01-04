package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.KhoaHoc;

@Repository("khoahocDao")
public interface KhoaHocDao extends JpaRepository<KhoaHoc, Integer> {

}
