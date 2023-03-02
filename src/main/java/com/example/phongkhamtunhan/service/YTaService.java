package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.entity.YTa;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import com.example.phongkhamtunhan.repository.LuongRepository;
import com.example.phongkhamtunhan.repository.YTaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class YTaService {
    @Autowired
    private YTaRepository yTaRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    @Autowired
    private LuongRepository luongRepo;
    public YTa create(YTa yTa) {
        yTa.setLuongCoBan(5000000.0);
        yTa.setLuongCongThem(200000.0);
        yTa.setViTri("YTa");
        return yTaRepo.save(yTa);
    }
    public YTa update(Long id,YTa yTa){
        yTa.setId(id);
        yTa.setLuongCoBan(5000000.0);
        yTa.setLuongCongThem(200000.0);
        yTa.setViTri("YTa");
        return yTaRepo.save(yTa);
    }
    @Transactional
    public void delete(Long id){
        lanKhamRepo.deleteAllByyTaId(id);
        luongRepo.deleteAllByNhanVienId(id);
        yTaRepo.deleteById(id);
    }
    public YTa getYTaId(Long id){
       return yTaRepo.findById(id).get();
    }
    public List<YTa> getYTa(String duLieu, String thuocTinh){
        List<YTa> yTaList = new ArrayList<>();
        switch (thuocTinh){
            case "maNV":
                yTaList = yTaRepo.findAllByMaNV(duLieu);
                break;
            case  "diaChi":
                yTaList = yTaRepo.findAllByDiaChi(duLieu);
                break;
            case "trinhDo":
                yTaList = yTaRepo.findAllByTrinhDo(duLieu);
                break;
            case "hoTen":
                yTaList = yTaRepo.findAllByHoTen(duLieu);
                break;
            default:
                break;
        }
        return yTaList;
    }
    public List<YTa> getFindAll(){
        return yTaRepo.findAll();
    }
}
