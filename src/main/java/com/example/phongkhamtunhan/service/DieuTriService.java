package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.DieuTri;
import com.example.phongkhamtunhan.entity.DieuTri_Thuoc;
import com.example.phongkhamtunhan.entity.LanKham;
import com.example.phongkhamtunhan.entity.compositykey.DieuTri_Thuoc_Key;
import com.example.phongkhamtunhan.repository.DieuTriRepository;
import com.example.phongkhamtunhan.repository.DieuTri_ThuocRepository;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import com.example.phongkhamtunhan.repository.ThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DieuTriService {
    @Autowired
    private DieuTriRepository dieuTriRepo;
    @Autowired
    private ThuocRepository thuocRepo;
    @Autowired
    private DieuTri_ThuocRepository dieuTri_thuocRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    public DieuTri create(DieuTri dt){
        DieuTri dieuTri = new DieuTri();
        dieuTri.setCachDung(dt.getCachDung());
        dieuTriRepo.save(dieuTri);
        Set<DieuTri_Thuoc> dttSet = new HashSet<>();
        for(DieuTri_Thuoc t:dt.getDieuTri_Thuocs()){
//            Tao primary key cho DieuTri_Thuoc
            DieuTri_Thuoc_Key dtt_key = new DieuTri_Thuoc_Key();
            dtt_key.setDieuTri(dieuTri.getId());
            dtt_key.setThuoc(t.getThuoc().getId());
            DieuTri_Thuoc dtt = new DieuTri_Thuoc();
            dtt.setDieuTri(dieuTri);
            dtt.setThuoc(thuocRepo.findById(t.getThuoc().getId()).get());
            dtt.setKey(dtt_key);
            dtt.setSoLuong(t.getSoLuong());
            dieuTri_thuocRepo.save(dtt);
            dtt.setDieuTri(null);
//            Luu danh sach DieuTri_Thuoc đã lưu vào cái Set mới
            dttSet.add(dtt);
        }
        dieuTri.setDieuTri_Thuocs(dttSet);
        return dieuTri;
    }
//    Thực hiện update lần khám và xóa các Điều trị tương ứng với lần khám đó mà không có danh sách DieuTri_Thuoc
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(Long id){
        List<DieuTri> dieuTriAll = dieuTriRepo.findAll();
        for(DieuTri dt: dieuTriAll){
            if(dt.getDieuTri_Thuocs().size()==1){
                for(DieuTri_Thuoc dtt: dt.getDieuTri_Thuocs()){
                    if(dtt.getThuoc().getId()==id){
                        LanKham lanKham = lanKhamRepo.findByDieuTriId(dt.getId());
                        lanKham.setDieuTri(null);
                        lanKham.setTongTienKham(null);
                        lanKhamRepo.save(lanKham);
                        dieuTriRepo.deleteById(dt.getId());
                    }
                }
            }
        }
    }
}
