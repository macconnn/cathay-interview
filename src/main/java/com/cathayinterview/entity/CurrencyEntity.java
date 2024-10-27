package com.cathayinterview.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    String c_id;
    @Column(name = "code")
    String code;
    @Column(name = "symbol")
    String symbol;
    @Column(name = "rate")
    String rate;
    @Column(name = "description")
    String description;
    @Column(name = "rate_float")
    double rate_float;

//    @Override
//    public String toString(){
//        return String.format("c_id : %s\n" +
//                        "code : %s\n" +
//                        "symbol : %s\n" +
//                        "rate : %s\n" +
//                        "description : %s\n" +
//                        "rate_float : %.4f",
//                c_id, code, symbol, rate, description, rate_float);
//    }
}

