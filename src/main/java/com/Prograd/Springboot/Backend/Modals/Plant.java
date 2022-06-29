package com.Prograd.Springboot.Backend.Modals;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="Plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int plant_id;
    @Column(nullable = false)
    private String plant_name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float cost;
}
