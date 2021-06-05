package com.roger.flippy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.roger.flippy.domain.Agent;
import com.roger.flippy.domain.Customer;
import com.roger.flippy.domain.Item;
import com.roger.flippy.domain.ShopPartner;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    @ApiModelProperty(allowableValues = "status ", position = 6)
    @JsonProperty(value = "status")
    private String status;
    @ApiModelProperty(allowableValues = "shopPartner ", position = 7)
    @JsonProperty(value = "shopPartner")
    private ShopPartner shopPartner;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate= new Date();
}
