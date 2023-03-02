package com.example.phongkhamtunhan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="yta")
@Data
public class YTa extends NhanVien implements Serializable {

}
