package com.example.phongkhamtunhan.api;

import com.example.phongkhamtunhan.entity.ThongKeBenh;
import com.example.phongkhamtunhan.service.BenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThongKeAPI {
    @Autowired
    private BenhService benhSer;
    @GetMapping("/thongkebenh")
    public List<ThongKeBenh> thongKe(@RequestParam(name="thang") String thang){
        return benhSer.thongKeBenh(thang);
    }
}
