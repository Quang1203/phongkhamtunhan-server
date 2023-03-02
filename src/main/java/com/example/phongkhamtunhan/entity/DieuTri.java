package com.example.phongkhamtunhan.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="dieutri")
@Getter
@Setter
@RequiredArgsConstructor
public class DieuTri implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cachdung")
    private String cachDung;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dieuTri",fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<DieuTri_Thuoc> dieuTri_Thuocs;
}
