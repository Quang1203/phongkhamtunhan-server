package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.Thuoc;
import com.example.phongkhamtunhan.entity.YTa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThuocRepository extends JpaRepository<Thuoc,Long> {
    List<Thuoc> findAllByMaTH(String maTH);
    List<Thuoc> findAllByDonGia(Double donGia);
    List<Thuoc> findAllByTenThuoc(String tenThuoc);
}
