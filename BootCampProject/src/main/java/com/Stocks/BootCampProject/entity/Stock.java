package com.Stocks.BootCampProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
//Todo: give table name (unique names/constants)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    private int stockId;

    private String stockName;

    private double openingPrice;

    private double closingPrice;

    private double highPrice;

    private double lowPrice;


}
