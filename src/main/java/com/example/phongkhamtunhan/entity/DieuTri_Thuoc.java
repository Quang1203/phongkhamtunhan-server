package com.example.phongkhamtunhan.entity;

import com.example.phongkhamtunhan.entity.compositykey.DieuTri_Thuoc_Key;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="dieutri_thuoc")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DieuTri_Thuoc implements Serializable {
    @EmbeddedId
    private DieuTri_Thuoc_Key key;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("thuoc")
    @JoinColumn(name="thuoc")
    private Thuoc thuoc;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dieuTri")
    @JoinColumn(name="dieutri")
    private DieuTri dieuTri;
    @Column(name="soluong")
    private Integer soLuong;
}
