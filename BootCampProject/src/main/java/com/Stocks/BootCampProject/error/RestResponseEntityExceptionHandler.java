package com.Stocks.BootCampProject.error;

import com.Stocks.BootCampProject.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorMessage> stockNotFoundException(StockNotFoundException stockNotFoundException){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,stockNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(TradeException.class)
    public ResponseEntity<ErrorMessage> tradeException(TradeException tradeException){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, tradeException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException userNotFoundException){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorMessage> handleException(HttpMessageNotReadableException httpMessageNotReadableException) {
//        System.out.println(httpMessageNotReadableException.toString());
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "TradeType Can be either BUY or SELL");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generalException(Exception e) {

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Something Went Wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}

