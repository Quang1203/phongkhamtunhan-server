package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.entity.Thuoc;
import com.example.phongkhamtunhan.repository.DieuTri_ThuocRepository;
import com.example.phongkhamtunhan.repository.ThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThuocService {
    @Autowired
    private ThuocRepository thuocRepo;
    @Autowired
    private DieuTri_ThuocRepository dieuTriRepo;
    @Autowired
    private DieuTriService dieuTriSer;
    public Thuoc create(Thuoc thuoc){
        return thuocRepo.save(thuoc);
    }

    public Thuoc update(Long id,Thuoc thuoc){
        thuoc.setId(id);
        return thuocRepo.save(thuoc);
    }
    @Transactional
    public void delete(Long id){
        dieuTriSer.delete(id);
        thuocRepo.deleteById(id);
    }
    public Thuoc getThuocById(Long id){
        return thuocRepo.findById(id).get();
    }
    public List<Thuoc> getThuocBy(String duLieu, String thuocTinh){
        List<Thuoc> thuocList = new ArrayList<>();
        switch (thuocTinh){
            case "maTH":
                thuocList = thuocRepo.findAllByMaTH(duLieu);
                break;
            case  "tenThuoc":
                thuocList = thuocRepo.findAllByTenThuoc(duLieu);
                break;
            case "donGia":
                try{
                    Double gia = Double.parseDouble(duLieu);
                    thuocList = thuocRepo.findAllByDonGia(gia);
                    break;
                }catch (Exception e){
                    return thuocList;
                }
            default:
                break;
        }
        return thuocList;
    }
    public List<Thuoc> getFindALL(){
        return thuocRepo.findAll();
    }
}
