package com.darkoum.darkoum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ventes")
@Getter
@Setter
@NoArgsConstructor
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;  // Relation ManyToOne avec Client

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;  // Relation ManyToOne avec Article

    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;  // Relation ManyToOne avec Pack

    @Column(name = "sale_date", nullable = false)
    private java.time.LocalDate saleDate;

    @Column(name = "total_amount", nullable = false)
    private float totalAmount;

    @Column(name = "payement_status", nullable = false)
    private String payementStatus;
}
