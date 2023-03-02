package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.Luong;
import com.example.phongkhamtunhan.entity.NhanVien;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import com.example.phongkhamtunhan.repository.LuongRepository;
import com.example.phongkhamtunhan.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LuongService {
    @Autowired
    private LuongRepository luongRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    @Autowired
    private NhanVienRepository nhanVienRepo;
//    Luong duoc tinh theo nhan vien bat dau chuoi kham laf start
    public List<Luong> tinhDsLuong(String thang){
        List<NhanVien> listNhanVien = nhanVienRepo.findAll();
        List<Luong> listLuong = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date start = sdf.parse(thang);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            cal.add(Calendar.MONTH,1);
            cal.add(Calendar.DATE,-1);
            Date finish = cal.getTime();
            for(NhanVien nhanVien:listNhanVien){
                Optional<Luong> oLuong = luongRepo.findByNhanVienIdAndThang(nhanVien.getId(),thang);
                Luong luong = new Luong();
                if(oLuong.isPresent()) luong = oLuong.get();
                luong.setNhanVien(nhanVien);
                luong.setThang(thang);
//                Tinh luong cho bac sy
                if(nhanVien.getViTri().equalsIgnoreCase("BacSy")) {
                    Long count = lanKhamRepo.countByBacSyIdAndTinhTrangKhamAndNgayRaVienBetween(nhanVien.getId(), "start",
                            start, finish);
                    luong.setTien(count * nhanVien.getLuongCongThem() + nhanVien.getLuongCoBan());
//                    Tinh luong cho y Ta
                }else{
                    Long count = lanKhamRepo.countByyTaIdAndNgayRaVienBetween(nhanVien.getId(),start,finish);
                    luong.setTien(nhanVien.getLuongCoBan()+nhanVien.getLuongCongThem()*count);
                }
                luongRepo.save(luong);
                listLuong.add(luong);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listLuong;
    }
}
