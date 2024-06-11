package com.ak.db.mysqlOne.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="prodect")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private String description;
}
