package com.Stocks.BootCampProject.service.stock;

import com.Stocks.BootCampProject.entity.Stock;
import com.Stocks.BootCampProject.error.StockNotFoundException;
import com.Stocks.BootCampProject.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImp implements StockService{

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> readStocksFromCsv(BufferedReader inputReader) throws IOException {
        String line = null;
        boolean firstLine = true;
        List<Stock> stockList = new ArrayList<>();
        while((line = inputReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] columns = line.split(",");
            int stockID = Integer.parseInt(columns[0]);
            String stockName = columns[1];
            double openingPrice = Double.parseDouble((columns[4]));
            double highPrice = Double.parseDouble((columns[5]));
            double lowPrice = Double.parseDouble((columns[6]));
            double closingPrice = Double.parseDouble((columns[8]));

            Stock stock = Stock.builder()
                    .stockId(stockID)
                    .stockName(stockName)
                    .openingPrice(openingPrice)
                    .closingPrice(closingPrice)
                    .highPrice(highPrice)
                    .lowPrice(lowPrice)
                    .build();

            stockList.add(stock);
        }
        return stockList;
    }

    public String updateStocks(MultipartFile file) throws IOException {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        List<Stock> inputs = readStocksFromCsv(inputReader);
        stockRepository.saveAll(inputs);
        return "Stock details updated successfully";
    }

    @Override
    public Stock detailsStocks(int stockId) throws StockNotFoundException {
         Optional<Stock> stock = stockRepository.findById(stockId);
         if(!stock.isPresent()){
             throw new StockNotFoundException("Stock Not Found For Id: "+ stockId);
         }
        return stock.get();

    }
}
