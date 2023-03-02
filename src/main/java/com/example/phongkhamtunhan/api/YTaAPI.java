package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.YTa;
import com.example.phongkhamtunhan.service.YTaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yta")
public class YTaAPI {
    @Autowired
    private YTaService yTaSer;
    @PostMapping
    public YTa create(@RequestBody YTa yTa){
        return yTaSer.create(yTa);
    }
    @PutMapping("/{id}")
    public YTa update(@PathVariable Long id,@RequestBody YTa yTa){
        return yTaSer.update(id,yTa);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        yTaSer.delete(id);
    }
    @GetMapping("/{id}")
    public YTa getYTaById(@PathVariable Long id){
        return yTaSer.getYTaId(id);
    }
    @GetMapping
    public List<YTa> getYTaBy(@RequestParam(name = "duLieu") String duLieu, @RequestParam(name = "thuocTinh") String thuocTinh){
        return yTaSer.getYTa(duLieu,thuocTinh);
    }
    @GetMapping("/all")
    public List<YTa> getFindAll(){
        return yTaSer.getFindAll();
    }
}
