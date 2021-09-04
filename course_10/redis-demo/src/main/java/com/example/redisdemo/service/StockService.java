package com.example.redisdemo.service;

public interface StockService {

    /**
     * 购买某件商品
     */
    void buyProduct(Long id) throws InterruptedException;

    void publish(String message);
}
