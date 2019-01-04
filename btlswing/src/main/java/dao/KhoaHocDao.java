package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Khoahoc;

@Repository("khoahocDao")
public interface KhoaHocDao extends JpaRepository<Khoahoc, Integer> {

}
