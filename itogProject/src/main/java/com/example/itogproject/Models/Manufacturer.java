package com.example.itogproject.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 90)
    private String name;

    @OneToMany(mappedBy = "manufacturer_boardRolePlayingGames")
    private Set<BoardRole_PlayingGames> boardRolePlayingGames;

    @OneToMany(mappedBy = "manufacturer_citadelColourPaints")
    private Set<CitadelColourPaints> citadelColourPaints;

    @OneToMany(mappedBy = "manufacturer_collectibleCardGames")
    private Set<CollectibleCardGames> collectibleCardGames;

    @OneToMany(mappedBy = "manufacturer_tableGames")
    private Set<TableGames> tableGames;

    @OneToMany(mappedBy = "manufacturer_war_games")
    private Set<Wargames> war_games;

    @OneToMany(mappedBy = "manufacturer_employees")
    private Set<Employee> employees;

}
