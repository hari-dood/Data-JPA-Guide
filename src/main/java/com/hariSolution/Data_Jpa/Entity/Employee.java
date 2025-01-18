package com.hariSolution.Data_Jpa.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,
            unique = true)
    private String firstName;
    @Column(nullable = false,
            unique = true)
    private String lastName;
    @Column(nullable = false,
            unique = true)
    private String experience;
    @Column(nullable = false,
            unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate dataBirth;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;
    @OneToOne
    @JoinColumn(name = "address id")
    private EmployeeAddress address;

    @ManyToOne
    @JoinColumn(name = "department id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_mission",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_id")
    )
    private List<Mission> missions;

}
