package com.Stocks.BootCampProject.mapper;

import com.Stocks.BootCampProject.dto.TradeDto;
import com.Stocks.BootCampProject.entity.Trade;

public class TradeMapper {

    public static TradeDto mapToTradeDto(Trade trade){

        TradeDto tradeDto = TradeDto.builder()
                .tradeType(trade.getTradeType())
                .quantity(trade.getQuantity())
                .userId(trade.getUserId())
                .stockId(trade.getStockId())
                .build();

        return tradeDto;
    }

    public static Trade mapToTrade(TradeDto tradeDto){

        Trade trade = Trade.builder().
                stockId(tradeDto.getStockId()).
                tradeType(tradeDto.getTradeType()).
                quantity(tradeDto.getQuantity()).
                userId(tradeDto.getUserId()).
                build();

        return trade;



    }
}
