package com.exampleEMS.EmployeeMS.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String roleName;
}
