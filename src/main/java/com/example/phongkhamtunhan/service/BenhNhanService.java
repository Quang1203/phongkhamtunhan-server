package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.entity.BenhNhan;
import com.example.phongkhamtunhan.repository.BenhNhanRepository;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BenhNhanService {
    @Autowired
    private BenhNhanRepository benhNhanRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    public BenhNhan create(BenhNhan benhNhan){
        return benhNhanRepo.save(benhNhan);
    }
    public BenhNhan update(Long id,BenhNhan benhNhan){
        benhNhan.setId(id);
        return benhNhanRepo.save(benhNhan);
    }
    @Transactional
    public void delete(Long id){
        lanKhamRepo.deleteAllByBenhNhanId(id);
        benhNhanRepo.deleteById(id);
    }
    public BenhNhan getBenhNhanById(Long id){
        return benhNhanRepo.findById(id).get();
    }
    public List<BenhNhan> getBenhNhanBy(String duLieu, String thuocTinh){
        List<BenhNhan> benhNhanList = new ArrayList<>();
        switch (thuocTinh){
            case "maBN":
                benhNhanList = benhNhanRepo.findAllByMaBN(duLieu);
                break;
            case  "diaChi":
                benhNhanList = benhNhanRepo.findAllByDiaChi(duLieu);
                break;
            case "hoTen":
                benhNhanList = benhNhanRepo.findAllByHoTen(duLieu);
                break;
            default:
                break;
        }
        return benhNhanList;
    }
    public List<BenhNhan> getFindAll(){
        return benhNhanRepo.findAll();
    }
}
