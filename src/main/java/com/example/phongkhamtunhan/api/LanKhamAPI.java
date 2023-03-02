package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.LanKham;
import com.example.phongkhamtunhan.service.LanKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lankham")
public class LanKhamAPI {
    @Autowired
    private LanKhamService lanKhamSer;
    @PostMapping
    public LanKham create(@RequestBody LanKham lanKham){
        return lanKhamSer.create(lanKham);
    }
    @GetMapping
    public LanKham getLanKhamByMa(@RequestParam(name="maLK") String maLK){
        return lanKhamSer.getLanKhamByMa(maLK);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        lanKhamSer.delete(id);
    }
    @GetMapping("/{id}")
    public LanKham getLanKhamById(@PathVariable Long id){
        return lanKhamSer.getLanKhamById(id);
    }
    @PutMapping("/{id}")
    public void updateLanKham(@PathVariable Long id,@RequestBody LanKham lanKham){
        lanKhamSer.updateLanKham(id,lanKham);
    }
    @GetMapping("/all")
    public List<LanKham> getDanhSach(@RequestParam(name="ngaybatdau") String ngayBatDau,
                                     @RequestParam(name="ngayketthuc") String ngayKetThuc){
        return lanKhamSer.getDanhSach(ngayBatDau,ngayKetThuc);
    }
}
