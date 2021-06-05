package com.roger.shoppartner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roger.shoppartner.domain.Agent;
import com.roger.shoppartner.domain.Customer;
import com.roger.shoppartner.domain.Item;
import com.roger.shoppartner.domain.ShopPartner;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @ApiModelProperty(allowableValues = "order Id ", position = 1)
    @JsonProperty(value = "orderId")
    private String orderId;
    @ApiModelProperty(allowableValues = "customer information ", position = 2)
    @JsonProperty(value = "customer")
    private Customer customer;
    @ApiModelProperty(allowableValues = "Items ordered ", position = 3)
    @JsonProperty(value = "items")
    private List<Item> itemsList;
    @ApiModelProperty(allowableValues = "Total order costs ", position = 4)
    @JsonProperty(value = "totalcost")
    private double total_cost;
    @ApiModelProperty(allowableValues = "Total number of items ordered ", position = 5)
    @JsonProperty(value = "numberofitems")
    private int number_of_items;
    @ApiModelProperty(allowableValues = "shopPartner ", position = 6)
    @JsonProperty(value = "shopPartner")
    private ShopPartner shopPartner;
    @ApiModelProperty(allowableValues = "agent ", position = 7)
    @JsonProperty(value = "agent")
    private Agent agent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate= new Date();
}
