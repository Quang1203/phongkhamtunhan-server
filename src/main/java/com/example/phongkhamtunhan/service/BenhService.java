package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.Benh;
import com.example.phongkhamtunhan.entity.LanKham;
import com.example.phongkhamtunhan.entity.ThongKeBenh;
import com.example.phongkhamtunhan.repository.BenhRepository;
import com.example.phongkhamtunhan.repository.LanKhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BenhService {
    @Autowired
    private BenhRepository benhRepo;
    @Autowired
    private LanKhamRepository lanKhamRepo;
    public Benh create(Benh benh){
        return benhRepo.save(benh);
    }
//    Thông kê bệnh được thực hiện thống kê theo ngày vào viện và bắt đầu chuỗi khám
    public List<ThongKeBenh> thongKeBenh(String thang){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<ThongKeBenh> listTK = new ArrayList<>();
        try {
//            ngay bat dau thong ke
            Date start = sdf.parse(thang);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            cal.add(Calendar.MONTH,1);
            cal.add(Calendar.DATE,-1);
//            ngay ket thuc thong ke
            Date finish = cal.getTime();
            List<LanKham> listLanKham = lanKhamRepo.findByTinhTrangKhamAndNgayVaoVienBetween("start",start,finish);
//           Lấy danh sách bệnh kềm số lần mắc bệnh
            listTK = filterThongKeBenh(listLanKham);
//            Săp xếp giảm đần theo số lần mắc bệnh
            Collections.sort(listTK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(listTK.size()!=0)return listTK;
        return null;
    }
    private List<ThongKeBenh> filterThongKeBenh(List<LanKham> listLanKham){
        List<ThongKeBenh> listTK = new ArrayList<>();
        List<Long> idsBenh = new ArrayList<>();
        for(LanKham l:listLanKham){
            int index = idsBenh.indexOf(l.getBenh().getId());
            if(index==-1){
                idsBenh.add(l.getBenh().getId());
                ThongKeBenh tkBenh = new ThongKeBenh();
                tkBenh.setBenh(l.getBenh());
                tkBenh.setSoLuong(1);
                listTK.add(tkBenh);
            }else{
                ThongKeBenh tkBenh = listTK.get(index);
                tkBenh.setSoLuong(tkBenh.getSoLuong()+1);
            }
        }
        return listTK;
    }

    public Benh update(Long id, Benh benh){
        benh.setId(id);
        return benhRepo.save(benh);
    }
    public List<Benh> getFindAll(){
        return benhRepo.findAll();
    }
    public Benh getBenhById(Long id){
        return benhRepo.findById(id).get();
    }
}
