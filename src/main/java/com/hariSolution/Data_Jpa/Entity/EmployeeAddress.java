package com.hariSolution.Data_Jpa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmployeeAddress {
    @Id
    private int id;
    private String streetName;
    private String houseName;
    private int pinCode;
    @OneToOne
    @JoinColumn(name = "employee id")
    private EmployeeAddress employee;
}
