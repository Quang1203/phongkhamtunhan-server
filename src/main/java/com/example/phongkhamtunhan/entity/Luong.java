package com.example.phongkhamtunhan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="luong")
public class Luong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="thang")
    private String thang;
    @Column(name="tien")
    private Double tien;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY,targetEntity = NhanVien.class)
    @JoinColumn(name = "nhanvien")
    private NhanVien nhanVien;
}
