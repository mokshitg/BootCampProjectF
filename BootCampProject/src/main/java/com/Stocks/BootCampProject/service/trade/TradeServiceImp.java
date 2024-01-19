package com.Stocks.BootCampProject.service.trade;

import com.Stocks.BootCampProject.Enum.TradeType;
import com.Stocks.BootCampProject.dto.TradeDto;
import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.error.TradeException;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.mapper.TradeMapper;
import com.Stocks.BootCampProject.repository.StockRepository;
import com.Stocks.BootCampProject.repository.TradeRepository;
import com.Stocks.BootCampProject.service.user.UserService;
import com.Stocks.BootCampProject.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImp implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    @Override
    public String placeTrade(TradeDto tradeDto) throws StockNotFoundException, TradeException, UserNotFoundException {

        Trade trade = TradeMapper.mapToTrade(tradeDto);
        TradeType tradeType = trade.getTradeType();


        userService.fetchUserById(trade.getUserId());
        Stock stock = stockService.detailsStocks(trade.getStockId());
        if(TradeType.BUY.equals(tradeType) &&
        trade.getQuantity()<=0){
            throw new TradeException("Quantity Not Appropriate");
        }

        if(TradeType.SELL.equals(tradeType) ){
            if(trade.getQuantity()<=0) {
                throw new TradeException("Quantity Not Appropriate");
            }
            else {
                int totalQuanity = 0;
                List<Trade> tradeListForUserAndStock = getAllTradesOfUserWithStock(trade.getUserId(), trade.getStockId());
                for(Trade uTrade : tradeListForUserAndStock){
                    if(TradeType.BUY.equals(uTrade.getTradeType())){
                        totalQuanity+=uTrade.getQuantity();
                    }else if (TradeType.SELL.equals(uTrade.getTradeType())) {
                        totalQuanity = totalQuanity - uTrade.getQuantity();
                    }
                }
                if(totalQuanity<trade.getQuantity()){
                    throw new TradeException("Insufficient Quantity = " + totalQuanity +
                            " For StockId : " + trade.getStockId());
                }
            }
        }

        trade.setBuyPrice(stock.getOpeningPrice());
        tradeRepository.save(trade);

        return "Trade Successful";
    }

    @Override
    public List<Trade> fetchAllTrades() {
        return tradeRepository.findAll();
    }

    public List<Trade> getAllTradesOfUserWithStock(int userId,int stockId){

        return tradeRepository.findByUserIdAndStockId(userId,stockId);
    }
}
