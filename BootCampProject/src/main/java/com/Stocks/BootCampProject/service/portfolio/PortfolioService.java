package com.Stocks.BootCampProject.service.portfolio;

import com.Stocks.BootCampProject.dto.Portfolio;
import com.Stocks.BootCampProject.error.StockNotFoundException;

import java.util.List;

public interface PortfolioService {
    public Portfolio fetchPortfolio(int userId) throws StockNotFoundException;
}
