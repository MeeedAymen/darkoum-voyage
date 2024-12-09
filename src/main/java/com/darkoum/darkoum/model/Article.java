package com.darkoum.darkoum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;  // Relation ManyToOne avec Provider

    @ManyToMany
    @JoinTable(
            name = "article_pack",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "pack_id"))
    private List<Pack> packs;  // Relation ManyToMany avec Pack
}
