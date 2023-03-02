package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.DieuTri_Thuoc;
import com.example.phongkhamtunhan.entity.compositykey.DieuTri_Thuoc_Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DieuTri_ThuocRepository extends JpaRepository<DieuTri_Thuoc,DieuTri_Thuoc_Key> {
    List<DieuTri_Thuoc> deleteAllByThuocId(Long id);
    void deleteAllByDieuTriId(Long id);
}
