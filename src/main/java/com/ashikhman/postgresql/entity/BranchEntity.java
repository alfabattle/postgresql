package com.ashikhman.postgresql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Branch")
@Table(name = "branches")
@Data
public class BranchEntity {

    @Id
    private int id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;
}
