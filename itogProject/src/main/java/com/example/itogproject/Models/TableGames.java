package com.example.itogproject.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Table_Games")
public class TableGames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 90, nullable = false)
    private String name;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String year_of_release;

    @ManyToOne
    @JoinColumn(name="manufacturer_id")
    private Manufacturer manufacturer_tableGames;

}
