package com.Stocks.BootCampProject.repository;

import com.Stocks.BootCampProject.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Integer> {

    public List<Trade> findByUserIdAndStockId(int userId, int stockId);

    public List<Trade> findByUserId(int userId);
}
