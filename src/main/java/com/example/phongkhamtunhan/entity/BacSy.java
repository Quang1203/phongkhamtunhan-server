package com.example.phongkhamtunhan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="bacsy")
@Data
public class BacSy extends NhanVien implements Serializable {
    @Column(name="chyenmon")
    private String chuyenMon;

}
