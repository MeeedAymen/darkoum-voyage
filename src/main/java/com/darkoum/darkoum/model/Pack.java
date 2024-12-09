package com.darkoum.darkoum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "packs")
@Getter
@Setter
@NoArgsConstructor
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;

    @ManyToMany(mappedBy = "packs")
    private List<Article> articles;  // Relation ManyToMany avec Article

    @OneToMany(mappedBy = "pack")
    private List<Vente> ventes;  // Relation OneToMany avec Vente
}
