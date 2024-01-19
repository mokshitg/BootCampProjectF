package com.Stocks.BootCampProject.service.stock;

import static org.junit.jupiter.api.Assertions.*;

import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.repository.StockRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@SpringBootTest
class StockServiceTest {

  @Autowired
  private StockService stockService;

  @MockBean
  private StockRepository stockRepository;

  private Stock stock;

  @BeforeEach
  void setUp() {
    stock = Stock.builder()
        .stockId(50009)
        .stockName("TATA")
        .closingPrice(20.0)
        .lowPrice(18)
        .highPrice(21)
        .openingPrice(19)
        .build();
  }

  @Test
  void testReadStocksFromCsv() throws IOException {

    StringReader reader = new StringReader("SC_CODE,SC_NAME,SC_GROUP,SC_TYPE,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,NO_TRADES,NO_OF_SHRS,NET_TURNOV,TDCLOINDI\n"+
        "500002,ABB LTD.,A,Q,4766,4836.5,4684.9,4745.45,4727.8,4742,1172,5884,28100618\n" +
        "500003,AEGIS LOGIS,A,Q,378.05,379.5,373.15,377.85,377.85,375.65,1703,51875,19522528\n" +
        "500008,ARE&M,A,Q,770.8,771.15,749.45,752,752,763.45,2091,29561,22358767");

    try{

      List<Stock> stockList = stockService.readStocksFromCsv(new BufferedReader(reader));

      assertEquals(3, stockList.size());
      assertEquals(500002, stockList.get(0).getStockId());
      assertEquals(378.05, stockList.get(1).getOpeningPrice());

    } catch (IOException e){
        fail(e);
    }

  }

  @Test
  void whenProvidedRightStockId_thenStockShouldBeReturned() throws StockNotFoundException {
    Mockito.when(stockRepository.findById(50009)).thenReturn(Optional.ofNullable((stock)));

    Stock found = stockService.detailsStocks(50009);

    assertEquals(stock,found);

  }
  @Test
  void whenWrongIdProvided_thenThrowStockNotFoundException() throws StockNotFoundException {
    Mockito.when(stockRepository.findById(50009)).thenReturn(Optional.empty());

//    Mockito.when(stockRepository.findById(50010)).thenReturn(Optional.ofNullable(stock));

    assertThrows(StockNotFoundException.class,()->stockService.detailsStocks(50009));
  }

}