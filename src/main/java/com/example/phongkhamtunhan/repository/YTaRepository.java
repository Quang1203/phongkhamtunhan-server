package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.entity.YTa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface YTaRepository extends JpaRepository<YTa,Long> {
    List<YTa> findAllByMaNV(String maNV);
    List<YTa> findAllByDiaChi(String diaChi);
    List<YTa> findAllByTrinhDo(String trinhDo);
    List<YTa> findAllByHoTen(String hoTen);
}
