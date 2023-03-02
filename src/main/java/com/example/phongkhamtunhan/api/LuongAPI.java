package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.Luong;
import com.example.phongkhamtunhan.service.LuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LuongAPI {
    @Autowired
    private LuongService luongSer;
    @GetMapping("/luong")
    public List<Luong> tinhLuong(@RequestParam(name="thang") String thang){
        List<Luong> list = luongSer.tinhDsLuong(thang);
        return list;
    }
}
