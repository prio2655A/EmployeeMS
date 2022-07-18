package com.exampleEMS.EmployeeMS.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int a_id;

    @Column(nullable = false)
    private String assetName;

    @Column(nullable = false)
    private int assetPrice;

    @ManyToOne
    @JsonBackReference
    private Organisation organisation;


}

