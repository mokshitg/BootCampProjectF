package com.Stocks.BootCampProject.entity;

import com.Stocks.BootCampProject.Enum.TradeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeId;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    private int quantity;

    private int userId;

    private int stockId;

    private double buyPrice;

}
