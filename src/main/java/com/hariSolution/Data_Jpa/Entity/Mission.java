package com.hariSolution.Data_Jpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameOfTheMission;
    private String duration;
    @ManyToMany(mappedBy = "missions")
    private List<Employee> employees;
}
