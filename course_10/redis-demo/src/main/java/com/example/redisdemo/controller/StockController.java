package com.example.redisdemo.controller;

import com.example.redisdemo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 模拟减库存
     */
    @GetMapping("buy")
    public ResponseEntity stock() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    stockService.buyProduct(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return ResponseEntity.ok().build();
    }

}
