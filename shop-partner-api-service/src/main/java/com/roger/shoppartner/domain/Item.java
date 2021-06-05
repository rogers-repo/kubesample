package com.roger.shoppartner.domain;

import lombok.Data;

@Data
public class Item {

    private String name;
    private int quantity;
    private double price_per_unit;
    private double total_price;
}
