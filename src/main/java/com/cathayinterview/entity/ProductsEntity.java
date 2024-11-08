package com.cathayinterview.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int p_id;

    @Column(name = "product_id")
    String product_id;
    @Column(name = "name")
    String name;
    @Column(name = "short_name")
    String short_name;
    @Column(name = "data_grouping")
    boolean data_grouping;


}
