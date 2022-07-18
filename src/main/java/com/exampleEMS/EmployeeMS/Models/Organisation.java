package com.exampleEMS.EmployeeMS.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int o_id;

    @Column(nullable = false)
    private String organisationName;

    @Column(nullable = false)
    private String address;


//    @OneToMany
//    private List<Asset> asset;

    @OneToMany(mappedBy="organisation",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Asset> asset;

    @OneToMany(mappedBy="organisation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employee;

}
