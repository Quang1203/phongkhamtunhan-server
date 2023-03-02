package com.example.phongkhamtunhan.repository;

import com.example.phongkhamtunhan.entity.LanKham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LanKhamRepository extends JpaRepository<LanKham,Long> {
    Optional<LanKham> findFirstByBenhNhanIdOrderByIdDesc(Long benhNhan);
    Long countByBacSyIdAndTinhTrangKhamAndNgayRaVienBetween(Long id, String tinhTrangKham,
                                                                   Date ngayBatDau, Date ngayKetThuc);
    Long countByyTaIdAndNgayRaVienBetween(Long id, Date ngayBatDau, Date ngayKetThuc);
    List<LanKham> findByTinhTrangKhamAndNgayVaoVienBetween(String status,Date ngayBatDau, Date ngayKetThuc);
    void deleteAllByBacSyId(Long id);
    void deleteAllByyTaId(Long id);
    void deleteAllByBenhNhanId(Long id);
    LanKham findByDieuTriId(Long id);
    Optional<LanKham> findByMaLanKham(String maLK);
    Optional<LanKham> findFirstByBenhNhanIdAndIdBeforeOrderByIdDesc(Long idBenhNhan, Long id);
    Optional<LanKham> findFirstByBenhNhanIdAndIdAfterOrderByIdAsc(Long idBenhNhan, Long id);
    List<LanKham> findByNgayVaoVienAfterAndNgayRaVienBefore(Date ngayBatDau, Date ngayKetThuc);
}
