package com.ashikhman.postgresql.dto;

import lombok.Data;

@Data
public class BranchDto {
    private int id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;

    private Long distance;
}
