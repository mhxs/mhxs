package com.example.redisdemo;

import com.example.redisdemo.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private StockService stockService;

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    stockService.buyProduct(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
