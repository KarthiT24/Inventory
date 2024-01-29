package com.Inventory.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProductId;

    private String Product;

    private Long StocksAvailable;
}
