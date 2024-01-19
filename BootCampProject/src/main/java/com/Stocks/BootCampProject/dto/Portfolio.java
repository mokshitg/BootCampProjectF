package com.Stocks.BootCampProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {

    private List<Holdings> holdingsList;

    private double totalHoldings;
    private double totalBuyPrice;

    private double totalProfitLoss;

    private double profitByLossPercent;

}
