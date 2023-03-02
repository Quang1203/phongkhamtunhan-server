package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.BacSy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BacSyRepository extends JpaRepository<BacSy,Long> {
    List<BacSy> findAllByMaNV(String maNV);
    List<BacSy> findAllByDiaChi(String diaChi);
    List<BacSy> findAllByTrinhDo(String trinhDo);
    List<BacSy> findAllByHoTen(String hoTen);
    List<BacSy> findAllByChuyenMon(String chuyenMon);
}
