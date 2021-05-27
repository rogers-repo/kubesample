package com.stackroute.deliveryagent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String orderId;
    private Customer customer;
    private ShopPartner shopPartner;
    private List<Item> itemsList;
    private double total_cost;
    private int number_of_items;
    private Date creationDate = new Date();
    private String status;
    private Agent agent;
}
