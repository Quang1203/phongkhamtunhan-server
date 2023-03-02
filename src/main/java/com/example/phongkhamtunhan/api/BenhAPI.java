package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.Benh;
import com.example.phongkhamtunhan.service.BenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benh")
public class BenhAPI {
    @Autowired
    private BenhService benhSer;
    @PostMapping
    public Benh create(@RequestBody Benh benh){
        return benhSer.create(benh);
    }
    @GetMapping
    public Benh getBenh(){
        return new Benh();
    }
    @PutMapping("/{id}")
    public Benh update(@PathVariable Long id,@RequestBody Benh benh){
        return benhSer.update(id,benh);
    }
    @GetMapping("/all")
    public List<Benh> getFindAll(){
        return benhSer.getFindAll();
    }
    @GetMapping("/{id}")
    public Benh getBenhById(@PathVariable Long id){
       return benhSer.getBenhById(id);
    }
}
