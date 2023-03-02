package com.example.phongkhamtunhan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@Table(name="benhnhan")
public class BenhNhan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cmt")
    private String cmt;
    @Column(name="ngaysinh",columnDefinition = "DATE")
    private Date ngaySinh;
    @Column(name="mabn",nullable = false)
    private String maBN;
    private String sdt;
    @Column(name="diachi")
    private String diaChi;
    @Column(name="hoten")
    private String hoTen;
}
