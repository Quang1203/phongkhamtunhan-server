package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.BenhNhan;
import com.example.phongkhamtunhan.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benhnhan")
public class BenhNhanAPI {
    @Autowired
    private BenhNhanService benhNhanSer;
    @PostMapping
    public BenhNhan create(@RequestBody BenhNhan benhNhan){
        return benhNhanSer.create(benhNhan);
    }
    @GetMapping
    public List<BenhNhan> getBenhNhan(@RequestParam(name = "duLieu") String duLieu, @RequestParam(name = "thuocTinh") String thuocTinh){
        return benhNhanSer.getBenhNhanBy(duLieu,thuocTinh);
    }
    @PutMapping("/{id}")
    public BenhNhan update(@PathVariable Long id,@RequestBody BenhNhan benhNhan){
        return benhNhanSer.update(id,benhNhan);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        benhNhanSer.delete(id);
    }
    @GetMapping("/{id}")
    public BenhNhan getBenhNhanById(@PathVariable Long id){
        return benhNhanSer.getBenhNhanById(id);
    }
    @GetMapping("/all")
    public List<BenhNhan> getFindAll(){
        return benhNhanSer.getFindAll();
    }
}
