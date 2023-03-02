package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.repository.BacSyRepository;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import com.example.phongkhamtunhan.repository.LuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BacSyService {
    @Autowired
    private BacSyRepository bacSyRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    @Autowired
    private LuongRepository luongRepo;
    public BacSy create(BacSy bacSy){
        bacSy.setLuongCoBan(7000000.0);
        bacSy.setLuongCongThem(1000000.0);
        bacSy.setViTri("BacSy");
        return bacSyRepo.save(bacSy);
    }
    public BacSy update(Long id,BacSy bacSy){
        bacSy.setId(id);
        bacSy.setLuongCoBan(7000000.0);
        bacSy.setLuongCongThem(1000000.0);
        bacSy.setViTri("BacSy");
        return bacSyRepo.save(bacSy);
    }
    @Transactional
    public void delete(Long id){
        lanKhamRepo.deleteAllByBacSyId(id);
        luongRepo.deleteAllByNhanVienId(id);
        bacSyRepo.deleteById(id);
    }
    public BacSy getBacSyById(Long id){
        return bacSyRepo.findById(id).get();
    }
    public List<BacSy> getBacSyBy(String duLieu, String thuocTinh){
        List<BacSy> bacSyList = new ArrayList<>();
        switch (thuocTinh){
            case "maNV":
                bacSyList = bacSyRepo.findAllByMaNV(duLieu);
                break;
            case  "diaChi":
                bacSyList = bacSyRepo.findAllByDiaChi(duLieu);
                break;
            case "trinhDo":
                bacSyList = bacSyRepo.findAllByTrinhDo(duLieu);
                break;
            case "hoTen":
                bacSyList = bacSyRepo.findAllByHoTen(duLieu);
                break;
            case "chuyenMon":
                bacSyList = bacSyRepo.findAllByChuyenMon(duLieu);
                break;
            default:
                break;
        }
        return bacSyList;
    }
    public List<BacSy> getfindAll(){
        return bacSyRepo.findAll();
    }
}
