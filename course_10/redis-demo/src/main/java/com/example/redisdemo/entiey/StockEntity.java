package com.example.redisdemo.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity implements Serializable {
    private Long id;
    private int stockNum;
}