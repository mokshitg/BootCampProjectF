package com.Stocks.BootCampProject.controller;

import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/ingest-from-csv")
    public ResponseEntity<String> updateStocks(@RequestParam("file") MultipartFile file) throws IOException {

        String message = stockService.updateStocks(file);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


    @GetMapping()
    public ResponseEntity<Stock> detailsStocks(@RequestParam("id") int id) throws StockNotFoundException {

        Stock stock = stockService.detailsStocks(id);
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }
}
