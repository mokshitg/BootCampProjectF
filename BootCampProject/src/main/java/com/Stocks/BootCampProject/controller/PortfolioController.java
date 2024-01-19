package com.Stocks.BootCampProject.controller;

import com.Stocks.BootCampProject.dto.Portfolio;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.service.portfolio.PortfolioService;
import com.Stocks.BootCampProject.service.stock.StockService;
import com.Stocks.BootCampProject.service.trade.TradeService;
import com.Stocks.BootCampProject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserService userService;

    @GetMapping("/portfolio")
    public ResponseEntity<Portfolio> fetchPortfolio(@RequestParam int userId) throws StockNotFoundException {

        Portfolio portfolio = portfolioService.fetchPortfolio(userId);
        return ResponseEntity.status(HttpStatus.OK).body(portfolio);
    }


}
