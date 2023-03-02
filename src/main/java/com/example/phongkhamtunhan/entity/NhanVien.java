package com.example.phongkhamtunhan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Table(name="nhanvien")
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="manv",nullable = false)
    private String maNV;
    @Column(name="hoten")
    private String hoTen;
    @Column(name="trinhdo")
    private String trinhDo;
    @Column(name="thamnien")
    private String thamNien;
    @Column(name="ngaysinh",columnDefinition = "DATE")
    private Date ngaySinh;
    private String cmt;
    private String sdt;
    @Column(name="diachi")
    private String diaChi;
    @Column(name="vitri")
    private String viTri;
    @Column(name="luongcoban")
    private Double luongCoBan;
    @Column(name="luongcongthem")
    private Double luongCongThem;
}
