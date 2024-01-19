package com.Stocks.BootCampProject.controller;

import com.Stocks.BootCampProject.dto.TradeDto;
import com.Stocks.BootCampProject.entity.Trade;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.error.TradeException;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<String> placeTrade(@RequestBody TradeDto tradeDto) throws StockNotFoundException, TradeException, UserNotFoundException {

        String message = tradeService.placeTrade(tradeDto);

        return ResponseEntity.status(HttpStatus.OK).body(message);

    }
    @GetMapping
    public List<Trade> fetchTrade(){
        return tradeService.fetchAllTrades();
    }

//    @GetMapping("/{userId}/{stockId}")
//    public List<Trade> findByUserIdAndStockId(@PathVariable("userId") int userId,@PathVariable("stockId") int stockId){
//        return tradeService.getAllTradesOfUserWithStock(userId,stockId);
//    }




}
