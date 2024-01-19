package com.Stocks.BootCampProject.service.stock;

import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface StockService {

    public List<Stock> readStocksFromCsv(BufferedReader inputReader) throws IOException;

    public String updateStocks(MultipartFile file) throws IOException;

    public Stock detailsStocks(int stock_id) throws StockNotFoundException;
}
