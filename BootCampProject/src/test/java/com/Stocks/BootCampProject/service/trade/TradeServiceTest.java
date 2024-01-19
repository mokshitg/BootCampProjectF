package com.Stocks.BootCampProject.service.trade;

import static org.junit.jupiter.api.Assertions.*;

import com.Stocks.BootCampProject.Enum.TradeType;
import com.Stocks.BootCampProject.dto.TradeDto;
import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.error.TradeException;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.mapper.TradeMapper;
import com.Stocks.BootCampProject.repository.TradeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TradeServiceTest {

  @Autowired
  private TradeService tradeService;

  @MockBean
  private TradeRepository tradeRepository;

  private Trade trade1;
  private Trade trade2;

  private Trade trade3;

  private List<Trade> tradeList = new ArrayList<>();


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
  }
  @Test
  void testFetchAllTrades() {

    Mockito.when(tradeRepository.findAll()).thenReturn(tradeList);

    List<Trade> foundList = tradeService.fetchAllTrades();

    assertEquals(foundList,tradeList);
  }

//  @Test
//  void placeTrade() throws UserNotFoundException, StockNotFoundException, TradeException {
//
//
//    TradeDto tradeDto1 = TradeMapper.mapToTradeDto(trade1);
//
//    String message = tradeService.placeTrade(tradeDto1);
//
//
//  }

}