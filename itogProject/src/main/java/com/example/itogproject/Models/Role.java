package com.example.itogproject.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 90, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Employee> employees;

}
