package com.example.phongkhamtunhan.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ThongKeBenh implements Serializable,Comparable<ThongKeBenh>{
    private Benh benh;
    private Integer soLuong;

    @Override
    public int compareTo(ThongKeBenh o) {
        return o.getSoLuong()-this.getSoLuong();
    }
}
