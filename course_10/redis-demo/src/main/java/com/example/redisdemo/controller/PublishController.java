package com.example.redisdemo.controller;

import com.example.redisdemo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private StockService stockService;

    /**
     * 模拟减库存
     */
    @GetMapping("publish")
    public ResponseEntity publish(String message) {
        stockService.publish(message);
        return ResponseEntity.ok().build();
    }

}
