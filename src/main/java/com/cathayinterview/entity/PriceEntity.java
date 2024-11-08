package com.cathayinterview.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "price")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int p_id;

    @Column(name = "product_id")
    String product_id;
    @Column(name = "date")
    String date;
    @Column(name = "price")
    double price;

}
