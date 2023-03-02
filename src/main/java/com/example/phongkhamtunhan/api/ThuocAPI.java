package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.Thuoc;
import com.example.phongkhamtunhan.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thuoc")
public class ThuocAPI {
    @Autowired
    private ThuocService thuocSer;
    @PostMapping
    public Thuoc create(@RequestBody Thuoc thuoc){
        return thuocSer.create(thuoc);
    }
    @GetMapping("/{id}")
    public Thuoc getThuocById(@PathVariable Long id){
        return thuocSer.getThuocById(id);
    }
    @PutMapping("/{id}")
    public Thuoc update(@PathVariable Long id,@RequestBody Thuoc thuoc){
        return thuocSer.update(id,thuoc);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        thuocSer.delete(id);
    }
    @GetMapping
    public List<Thuoc> getThuocBy(@RequestParam(name = "duLieu") String duLieu,@RequestParam(name = "thuocTinh") String thuocTinh){
        return thuocSer.getThuocBy(duLieu,thuocTinh);
    }
    @GetMapping("/all")
    public List<Thuoc> getFindAll(){
        return thuocSer.getFindALL();
    }
}
