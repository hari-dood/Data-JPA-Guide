package com.hariSolution.Data_Jpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String manager;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}
