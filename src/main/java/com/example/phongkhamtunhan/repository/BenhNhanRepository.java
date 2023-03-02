package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BenhNhanRepository extends JpaRepository<BenhNhan,Long> {
    List<BenhNhan> findAllByMaBN(String maNV);
    List<BenhNhan> findAllByDiaChi(String diaChi);

    List<BenhNhan> findAllByHoTen(String hoTen);

}
