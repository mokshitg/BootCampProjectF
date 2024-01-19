package com.Stocks.BootCampProject.dto;

import com.Stocks.BootCampProject.Enum.TradeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDto {

    @NotBlank(message = "Trade Type is Mandatory")
    private TradeType tradeType;

    @NotBlank(message = "Quantity is Mandatory")
    private int quantity;


    @NotBlank(message = "User ID is Mandatory")
    private int userId;


    @NotBlank(message = "Stock ID is Mandatory")
    private int stockId;
}
