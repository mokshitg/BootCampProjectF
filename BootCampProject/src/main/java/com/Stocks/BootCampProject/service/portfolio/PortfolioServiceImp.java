package com.Stocks.BootCampProject.service.portfolio;

import com.Stocks.BootCampProject.Enum.TradeType;
import com.Stocks.BootCampProject.dto.Holdings;
import com.Stocks.BootCampProject.dto.Portfolio;
import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.repository.StockRepository;
import com.Stocks.BootCampProject.repository.TradeRepository;
import com.Stocks.BootCampProject.repository.UserRepository;
import com.Stocks.BootCampProject.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioServiceImp implements PortfolioService{

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockService stockService;

    public Portfolio fetchPortfolio(int userId) throws StockNotFoundException {

        List<Trade> tradeList = tradeRepository.findByUserId(userId);

        Map<Integer, Holdings> holdingsMap = new HashMap<>();

        for(Trade trade : tradeList){

            Stock stock = stockService.detailsStocks(trade.getStockId());
            double gainLoss = (stock.getClosingPrice()-trade.getBuyPrice())* trade.getQuantity();
            if(holdingsMap.containsKey(trade.getStockId())){

                Holdings holdings = holdingsMap.get(trade.getStockId());

                if(trade.getTradeType().equals(TradeType.BUY)){


                    holdings.setBuyPrice((holdings.getBuyPrice()*holdings.getQuantity() +
                            trade.getBuyPrice()*trade.getQuantity())/(holdings.getQuantity()+trade.getQuantity()));
                    holdings.setQuantity(holdings.getQuantity() + trade.getQuantity());
                }
                else {

                    holdings.setBuyPrice((holdings.getBuyPrice()*holdings.getQuantity() -
                            trade.getBuyPrice()*trade.getQuantity())/(holdings.getQuantity()-trade.getQuantity()));
                    holdings.setQuantity(holdings.getQuantity() - trade.getQuantity());
                }

                holdings.setGainLoss(stock.getClosingPrice()-(holdings.getBuyPrice())*holdings.getQuantity());


            }
            else {
                Holdings holdings = Holdings.builder()
                        .stockName(stock.getStockName())
                        .stockId(trade.getStockId())
                        .quantity(trade.getQuantity())
                        .buyPrice(trade.getBuyPrice())
                        .currentPrice(stock.getClosingPrice())
                        .gainLoss(gainLoss)
                        .build();

                holdingsMap.put(trade.getStockId(),holdings);
            }
        }
        double totalHoldings = 0;
        double totalBuyPrice = 0;
        double totalProfitLoss = 0;
        double profitByLossPercent = 0;

        for(Holdings holdings : holdingsMap.values()){

            totalHoldings += holdings.getCurrentPrice()*holdings.getQuantity();
            totalBuyPrice += holdings.getBuyPrice()*holdings.getQuantity();

        }
        totalProfitLoss = totalHoldings - totalBuyPrice;
        profitByLossPercent = (totalProfitLoss/totalBuyPrice)*100;

        Portfolio portfolio = Portfolio.builder()
                .holdingsList(new ArrayList<>(holdingsMap.values()))
                .totalHoldings(totalHoldings)
                .totalBuyPrice(totalBuyPrice)
                .totalProfitLoss(totalProfitLoss)
                .profitByLossPercent(profitByLossPercent)
                .build();

        return portfolio;

    }


}
