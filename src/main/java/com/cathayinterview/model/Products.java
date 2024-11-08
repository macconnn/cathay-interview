package com.cathayinterview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    String product_id;
    String name;
    String short_name;
    boolean data_grouping;
}
