package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.Luong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuongRepository extends JpaRepository<Luong,Long> {
    void deleteAllByNhanVienId(Long id);
    Optional<Luong> findByNhanVienIdAndThang(Long id,String thang);
}
