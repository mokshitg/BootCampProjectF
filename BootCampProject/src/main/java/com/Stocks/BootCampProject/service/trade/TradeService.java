package com.Stocks.BootCampProject.service.trade;

import com.Stocks.BootCampProject.dto.TradeDto;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.error.TradeException;
import com.Stocks.BootCampProject.error.UserNotFoundException;

import java.util.List;

public interface TradeService {

    public String placeTrade(TradeDto tradeDto) throws StockNotFoundException, TradeException, UserNotFoundException;

    public List<Trade> fetchAllTrades();

    public List<Trade> getAllTradesOfUserWithStock(int userId,int stockId);
}
