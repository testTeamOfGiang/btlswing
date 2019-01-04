package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.HocvienKhoahoc;
import model.HocvienKhoahocPK;

@Repository("hocvien_khoahocDao")
public interface HocVienKhoaHocDao extends JpaRepository<HocvienKhoahoc, HocvienKhoahocPK> {

}
