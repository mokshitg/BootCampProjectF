package com.Stocks.BootCampProject.service.portfolio;

import static org.junit.jupiter.api.Assertions.*;

import com.Stocks.BootCampProject.Enum.TradeType;
import com.Stocks.BootCampProject.dto.Holdings;
import com.Stocks.BootCampProject.dto.Portfolio;
import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.repository.StockRepository;
import com.Stocks.BootCampProject.repository.TradeRepository;
import com.Stocks.BootCampProject.service.stock.StockService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@SpringBootTest
class PortfolioServiceTest {
  @Autowired
  private PortfolioService portfolioService;

  @MockBean
  private TradeRepository tradeRepository;
  @MockBean
  private StockService stockService;

  private Trade trade1;
  private Trade trade2;

  private Trade trade3;

  private Stock stock;

  private List<Trade> tradeList = new ArrayList<>();

  private List<Holdings>  holdingsList = new ArrayList<>();
  private Holdings holdings;

  @BeforeEach
  void setUp() {

    trade1 = Trade.builder()
        .tradeType(TradeType.BUY)
        .stockId(500009)
        .userId(1)
        .quantity(10)
        .build();

    trade2 = Trade.builder()
        .tradeType(TradeType.BUY)
        .stockId(500009)
        .userId(1)
        .quantity(10)
        .build();

    trade3 = Trade.builder()
        .tradeType(TradeType.SELL)
        .stockId(500009)
        .userId(1)
        .quantity(10)
        .build();

    tradeList.add(trade1);
    tradeList.add(trade2);
    tradeList.add(trade3);

    stock = Stock.builder()
        .stockId(500009)
        .stockName("TATA")
        .closingPrice(10)
        .lowPrice(18)
        .highPrice(21)
        .openingPrice(10)
        .build();

    holdings = Holdings.builder()
        .currentPrice(10)
        .buyPrice(0)
        .gainLoss(10)
        .stockName("TATA")
        .stockId(500009)
        .quantity(10)
        .build();

  }

  @Test
  void fetchPortfolio() throws StockNotFoundException {

    Mockito.when(tradeRepository.findByUserId(1)).thenReturn(tradeList);
    Mockito.when(stockService.detailsStocks(500009)).thenReturn(stock);

    Portfolio portfolioFound = portfolioService.fetchPortfolio(1);

    assertEquals(holdings,portfolioFound.getHoldingsList().get(0));
  }
}