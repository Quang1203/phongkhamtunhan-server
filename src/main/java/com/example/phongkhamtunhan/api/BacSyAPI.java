package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.BacSy;
import com.example.phongkhamtunhan.service.BacSyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bacsy")
public class BacSyAPI {
    @Autowired
    private BacSyService bacSySer;
    @PostMapping
    public BacSy create(@RequestBody BacSy bacSy){
       return bacSySer.create(bacSy);
    }
    @PutMapping("/{id}")
    public BacSy update(@PathVariable Long id,@RequestBody BacSy bacSy){
        return bacSySer.update(id,bacSy);
    }
    @GetMapping("/{id}")
    public BacSy getBacSyById(@PathVariable Long id){
        return bacSySer.getBacSyById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bacSySer.delete(id);
    }
    @GetMapping
    public List<BacSy> getBacSyBy(@RequestParam(name = "duLieu") String duLieu,@RequestParam(name = "thuocTinh") String thuocTinh){
        return bacSySer.getBacSyBy(duLieu,thuocTinh);
    }
    @GetMapping("/all")
    public List<BacSy> getFindAll(){
        return bacSySer.getfindAll();
    }
}
