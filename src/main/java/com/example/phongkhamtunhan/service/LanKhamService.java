package com.example.phongkhamtunhan.service;

import com.example.phongkhamtunhan.entity.*;
import com.example.phongkhamtunhan.entity.compositykey.DieuTri_Thuoc_Key;
import com.example.phongkhamtunhan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LanKhamService {
    @Autowired
    private LanKhamRepository lanKhamRepo;
    @Autowired
    private BacSyRepository bacSyRepo;
    @Autowired
    private YTaRepository yTaRepo;
    @Autowired
    private BenhRepository benhRepo;
    @Autowired
    private BenhNhanRepository benhNhanRepo;
    @Autowired
    private DieuTriService dieuTriSer;
    @Autowired
    private ThuocRepository thuocRepo;
    @Autowired
    private DieuTriRepository dieuTriRepo;
    public LanKham create(LanKham lanKham){
//        Lay thong tin bacsy, yTa, benh, benh nhan tương ứng theo id được gửi về
        BacSy bacSy = bacSyRepo.findById(lanKham.getBacSy().getId()).get();
        YTa yTa = yTaRepo.findById(lanKham.getYTa().getId()).get();
        Benh benh = benhRepo.findById(lanKham.getBenh().getId()).get();
        BenhNhan benhNhan = benhNhanRepo.findById(lanKham.getBenhNhan().getId()).get();
//        Lay làn khám gần nhất của bệnh nhân
        Optional<LanKham> oLanKhamTruoc =
                lanKhamRepo.findFirstByBenhNhanIdOrderByIdDesc(lanKham.getBenhNhan().getId());
//        Nếu tồn tại lần khám trước đó thì kiểm tra để setTinhTrangKham là start hoặc continue cho lần khám hiện tại
        if(oLanKhamTruoc.isPresent()){
            LanKham lanKhamTruoc = oLanKhamTruoc.get();
//         Nếu như bệnh lần khám trước khác với bệnh hiện tại thì bệnh hiện tại sẽ được bắt đầu là chuối mới
//            setTinhTrangKham = start
            if(lanKhamTruoc.getBenh().getId() != lanKham.getBenh().getId()){
                lanKham.setTinhTrangKham("start");
//                Nếu cùng bệnh với lần khám trước thì phải cùng bác sỹ thì mới được( do đề yêu cầu)
            }else{
//               Không cùng bác sỹ
                if(bacSy.getId() != lanKhamTruoc.getBacSy().getId())return null;
//                cùng bệnh với lần khám trước được setTinhTrangKham continue
                lanKham.setTinhTrangKham("continue");
            }
//        Nếu không tồn tại lần khám trước đó thì setTinhTrangKham của lần khám hiện tại lá start
        }else{
            lanKham.setTinhTrangKham("start");
        }
//
        lanKham.setBacSy(bacSy);
        lanKham.setYTa(yTa);
        lanKham.setBenh(benh);
        lanKham.setBenhNhan(benhNhan);
        if(lanKham.getDieuTri()!=null){
//            Thuc hien tao dieu tri moi
            lanKham.setDieuTri(dieuTriSer.create(lanKham.getDieuTri()));
            Set<DieuTri_Thuoc> dttSet = lanKham.getDieuTri().getDieuTri_Thuocs();
            lanKham.getDieuTri().setDieuTri_Thuocs(null);
//            tinh tong tien kham
            lanKham.setTongTienKham(tinhTongTienKham(dttSet));
            lanKhamRepo.save(lanKham);
//            Tra lai danh sanh thuoc
            lanKham.getDieuTri().setDieuTri_Thuocs(dttSet);
        }else{
            lanKhamRepo.save(lanKham);
        }
        return  lanKham;
    }
    private Double tinhTongTienKham(Set<DieuTri_Thuoc> dttSet){
        Double tong =0.0;
        for(DieuTri_Thuoc dtt:dttSet){
            tong = tong + dtt.getSoLuong()*dtt.getThuoc().getDonGia();
        }
        return tong;
    }
    public void delete(Long id){
        Optional<LanKham> oLanKham = lanKhamRepo.findById(id);
        if(oLanKham.isPresent()){
            LanKham lanKham = oLanKham.get();
            Optional<LanKham> oLanKhamBefore =
                    lanKhamRepo.findFirstByBenhNhanIdAndIdBeforeOrderByIdDesc(lanKham.getBenhNhan().getId(),lanKham.getId());
            Optional<LanKham> oLanKhamAfter =
                    lanKhamRepo.findFirstByBenhNhanIdAndIdAfterOrderByIdAsc(lanKham.getBenhNhan().getId(),lanKham.getId());
//            Không tồn tại lần khám sau
            if(oLanKhamAfter.isEmpty()){
                lanKhamRepo.deleteById(id);
            }
//            Không tồn tại lần khám trước
            else if(oLanKhamBefore.isEmpty()){
                oLanKhamAfter.get().setTinhTrangKham("start");
                lanKhamRepo.save(oLanKhamAfter.get());
                lanKhamRepo.deleteById(id);
            }
//            Ca 2 lan kham deu ton tai
            else {
//                Giong benh
                if(oLanKhamBefore.get().getBenh().getId()==oLanKhamAfter.get().getBenh().getId()){
                    oLanKhamAfter.get().setTinhTrangKham("continue");
//                 Khac benh
                }else{
                    oLanKhamAfter.get().setTinhTrangKham("start");
                }
                lanKhamRepo.save(oLanKhamAfter.get());
                lanKhamRepo.deleteById(id);
            }
        }
    }
    public LanKham getLanKhamByMa(String maLK){
        Optional<LanKham> oLanKham = lanKhamRepo.findByMaLanKham(maLK);
        if(oLanKham.isPresent()){
            LanKham lanKham = oLanKham.get();
            if(lanKham.getDieuTri()!=null){
                for(DieuTri_Thuoc dtt: lanKham.getDieuTri().getDieuTri_Thuocs()){
                    dtt.setDieuTri(null);
                }
            }
            return lanKham;
        }
        return null;
    }
    public LanKham getLanKhamById(Long id){
        Optional<LanKham> oLanKham = lanKhamRepo.findById(id);
        if(oLanKham.get().getDieuTri()!=null){
            for(DieuTri_Thuoc dtt: oLanKham.get().getDieuTri().getDieuTri_Thuocs()){
                dtt.setDieuTri(null);
            }
        }
        return oLanKham.get();
    }
    public void updateLanKham(Long id, LanKham lanKham){
       LanKham lanKhamOld = lanKhamRepo.findById(id).get();
       updateLanKhamOld(lanKhamOld);
       BacSy bacSy = bacSyRepo.findById(lanKham.getBacSy().getId()).get();
       YTa yTa = yTaRepo.findById(lanKham.getYTa().getId()).get();
       BenhNhan benhNhan = benhNhanRepo.findById(lanKham.getBenhNhan().getId()).get();
       Benh benh = benhRepo.findById(lanKham.getBenh().getId()).get();
       lanKhamOld.setBacSy(bacSy);
       lanKhamOld.setYTa(yTa);
       lanKhamOld.setBenhNhan(benhNhan);
       lanKhamOld.setBenh(benh);
       lanKhamOld.setNgayVaoVien(lanKham.getNgayVaoVien());
       lanKhamOld.setNgayRaVien(lanKham.getNgayRaVien());
       if(lanKham.getDieuTri() != null && lanKhamOld.getDieuTri() == null){
           DieuTri dieuTri = dieuTriSer.create(lanKham.getDieuTri());
           lanKhamOld.setDieuTri(dieuTri);
           lanKhamOld.setTongTienKham(tinhTongTienKham(dieuTri.getDieuTri_Thuocs()));
           lanKhamRepo.save(lanKhamOld);
       }else if(lanKham.getDieuTri() == null && lanKhamOld.getDieuTri() == null){
           lanKhamRepo.save(lanKhamOld);
       }else if(lanKham.getDieuTri() == null){
           Long idDieuTri = lanKhamOld.getDieuTri().getId();
           lanKhamOld.setDieuTri(null);
           lanKhamOld.setTongTienKham(null);
           lanKhamRepo.save(lanKhamOld);
           dieuTriRepo.deleteById(idDieuTri);
       }
       else{
           lanKhamOld.getDieuTri().setCachDung(lanKham.getDieuTri().getCachDung());
           updateDieuTri_Thuoc(lanKham);
           lanKhamOld.setTongTienKham(tinhTongTienKham(lanKhamOld.getDieuTri().getDieuTri_Thuocs()));
           lanKhamRepo.save(lanKhamOld);
       }
       updateLanKhamNew(lanKhamOld);

    }
    public void updateDieuTri_Thuoc(LanKham lanKham){
        LanKham lanKhamOld = lanKhamRepo.findById(lanKham.getId()).get();
        Set<DieuTri_Thuoc> dttsNew = lanKham.getDieuTri().getDieuTri_Thuocs();
        Set<DieuTri_Thuoc> dttsOld = lanKhamOld.getDieuTri().getDieuTri_Thuocs();
        dttsOld.clear();
        DieuTri dieuTriOld = lanKhamOld.getDieuTri();
        for(DieuTri_Thuoc dtt:dttsNew){
            DieuTri_Thuoc_Key dtt_key = new DieuTri_Thuoc_Key();
            dtt_key.setDieuTri(dieuTriOld.getId());
            dtt_key.setThuoc(dtt.getThuoc().getId());
            DieuTri_Thuoc dttNew = new DieuTri_Thuoc();
            dttNew.setDieuTri(dieuTriOld);
            dttNew.setThuoc(thuocRepo.findById(dtt.getThuoc().getId()).get());
            dttNew.setKey(dtt_key);
            dttNew.setSoLuong(dtt.getSoLuong());
            dttsOld.add(dttNew);
        }
    }
    public void updateLanKhamOld(LanKham lanKham){
        Optional<LanKham> oLanKhamBefore =
                lanKhamRepo.findFirstByBenhNhanIdAndIdBeforeOrderByIdDesc(lanKham.getBenhNhan().getId(),lanKham.getId());
        Optional<LanKham> oLanKhamAfter =
                lanKhamRepo.findFirstByBenhNhanIdAndIdAfterOrderByIdAsc(lanKham.getBenhNhan().getId(),lanKham.getId());
//            Không tồn tại lần khám trước
        if(oLanKhamBefore.isEmpty() && oLanKhamAfter.isPresent()){
            oLanKhamAfter.get().setTinhTrangKham("start");
        }
//            Ca 2 lan kham deu ton tai
        else if(oLanKhamBefore.isPresent() && oLanKhamAfter.isPresent()){
//                Giong benh
            if(oLanKhamBefore.get().getBenh().getId()==oLanKhamAfter.get().getBenh().getId()){
                oLanKhamAfter.get().setTinhTrangKham("continue");
//                 Khac benh
            }else{
                oLanKhamAfter.get().setTinhTrangKham("start");
            }
        }
        if(oLanKhamAfter.isPresent())lanKhamRepo.save(oLanKhamAfter.get());
    }
    public void updateLanKhamNew(LanKham lanKham){
        Optional<LanKham> oLanKhamBefore =
                lanKhamRepo.findFirstByBenhNhanIdAndIdBeforeOrderByIdDesc(lanKham.getBenhNhan().getId(),lanKham.getId());
        Optional<LanKham> oLanKhamAfter =
                lanKhamRepo.findFirstByBenhNhanIdAndIdAfterOrderByIdAsc(lanKham.getBenhNhan().getId(),lanKham.getId());
//            Không tồn tại lần khám trước
        if(oLanKhamBefore.isEmpty()){
            lanKham.setTinhTrangKham("start");
        }
//      Ton tai lan kham truoc
        else if(oLanKhamBefore.isPresent()){
//                Giong benh
            if(oLanKhamBefore.get().getBenh().getId()== lanKham.getBenh().getId()){
                lanKham.setTinhTrangKham("continue");
//                 Khac benh
            }else{
                lanKham.setTinhTrangKham("start");
            }
        }

//      Ton tai lan kham sau
        if(oLanKhamAfter.isPresent()){
//                Giong benh
            if(oLanKhamAfter.get().getBenh().getId()== lanKham.getBenh().getId()){
                oLanKhamAfter.get().setTinhTrangKham("continue");
//                 Khac benh
            }else{
                oLanKhamAfter.get().setTinhTrangKham("start");
            }
            lanKhamRepo.save(oLanKhamAfter.get());
        }

    }
    public List<LanKham> getDanhSach(String ngayBatDau, String ngayKetThuc){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date finish = null;
        try {
            start = sdf.parse(ngayBatDau);
            finish = sdf.parse(ngayKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<LanKham> lanKhams = lanKhamRepo.findByNgayVaoVienAfterAndNgayRaVienBefore(start,finish);
        for(LanKham lanKham: lanKhams){
            if(lanKham.getDieuTri() != null) {
                for (DieuTri_Thuoc dtt : lanKham.getDieuTri().getDieuTri_Thuocs()) {
                    dtt.setDieuTri(null);
                }
            }
        }
        return lanKhams;
    }
}
