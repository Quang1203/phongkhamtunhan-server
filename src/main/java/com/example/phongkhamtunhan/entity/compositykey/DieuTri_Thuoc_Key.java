package com.example.phongkhamtunhan.entity.compositykey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DieuTri_Thuoc_Key implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="dieutri")
    private Long dieuTri;
    @Column(name="thuoc")
    private Long thuoc;
}
