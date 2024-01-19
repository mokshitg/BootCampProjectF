package com.Stocks.BootCampProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Holdings {

    private String stockName;
    private int stockId;
    private int quantity;
    private double buyPrice;
    private double currentPrice;

    private double gainLoss;

}
