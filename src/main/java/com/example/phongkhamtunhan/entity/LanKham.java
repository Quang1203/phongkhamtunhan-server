package com.example.phongkhamtunhan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="lankham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanKham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ngayvaovien",columnDefinition = "DATE")
    private Date ngayVaoVien;
    @Column(name="ngayravien",columnDefinition = "DATE")
    private Date ngayRaVien;
    @Column(name="malankham")
    private String maLanKham;
    @Column(name="tongtienkham")
    private Double tongTienKham;
    @Column(name="tinhtrangkham")
    private String tinhTrangKham;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},targetEntity = BenhNhan.class)
    @JoinColumn(name="benhnhan")
    private BenhNhan benhNhan;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},targetEntity = BacSy.class)
    @JoinColumn(name="bacsy")
    private BacSy bacSy;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},targetEntity = YTa.class)
    @JoinColumn(name="yta")
    private YTa yTa;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},targetEntity = Benh.class)
    @JoinColumn(name="benh")
    private Benh benh;
    @OneToOne(cascade = CascadeType.ALL,targetEntity = DieuTri.class)
    @JoinColumn(name = "dieutri")
    private DieuTri dieuTri;
}
