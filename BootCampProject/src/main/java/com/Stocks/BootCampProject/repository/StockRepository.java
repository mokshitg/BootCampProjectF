package com.Stocks.BootCampProject.repository;

import com.Stocks.BootCampProject.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {
}
