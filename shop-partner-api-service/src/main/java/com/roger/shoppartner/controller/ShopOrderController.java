package com.roger.shoppartner.controller;

import com.roger.shoppartner.domain.Order;
import com.roger.shoppartner.dto.OrderDto;
import com.roger.shoppartner.exception.OrderNotFoundException;
import com.roger.shoppartner.exception.ShopNotExist;
import com.roger.shoppartner.service.KafkaService;
import com.roger.shoppartner.service.ShopOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/shop/orders")
@Slf4j
public class ShopOrderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private KafkaService kafkaService;


    /*
    Define a handler methods:
    GET /api/shop/orders?shopId={shopId}
    GET /api/shop/orders/{orderId}?shopId={shopId}
    PUT /api/shop/orders/{orderId}/accept?shopId={shopId}
    PUT /api/shop/orders/{orderId}/ready?shopId={shopId}
    PUT /api/shop/orders/{orderId}/reject?shopId={shopId}
    */

    @GetMapping("")
    @ApiOperation(value = "Get all Shop orders",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer orders"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderDto>> getAllShopOrders(@RequestParam(value = "shopId") String shopId,@RequestParam(value = "per_page",defaultValue = "5") String per_page,@RequestParam(value = "page",defaultValue = "0") String page) throws ShopNotExist {
        List<Order> shopOrder= shopOrderService.getAllShopOrders(shopId,per_page,page);
        return new ResponseEntity<>(convertToOrderDtoList(shopOrder), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Get order by order id and shop id",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer orders"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> getOrderByIdAndShopId(@PathVariable String orderId, @RequestParam(value = "shopId") String shopId) throws ShopNotExist {
        Order shopOrder= shopOrderService.getOrderByIdAndShopId(orderId,shopId);
        return new ResponseEntity<>(convertToOrderDto(shopOrder), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/accept")
    @ApiOperation(value = "update order status to accepted ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated orders to accept state"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToAccepted(@PathVariable String orderId, @RequestParam(value = "shopId") String shopId) throws ShopNotExist, OrderNotFoundException {
        Order shopOrder= shopOrderService.updateOrderStatus(orderId,shopId,"accept");
        kafkaService.sendMessageToCustomer(shopOrder);
        kafkaService.sendMessageTodelivery(shopOrder);
        return new ResponseEntity<>(convertToOrderDto(shopOrder), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ready")
    @ApiOperation(value = "update order status to ready ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated orders to ready state"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToReady(@PathVariable String orderId, @RequestParam(value = "shopId") String shopId) throws ShopNotExist, OrderNotFoundException {
        Order shopOrder= shopOrderService.updateOrderStatus(orderId,shopId,"ready");
        kafkaService.sendMessageTodelivery(shopOrder);
        return new ResponseEntity<>(convertToOrderDto(shopOrder), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/reject")
    @ApiOperation(value = "update order status to rejected ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated orders to reject state"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToReject(@PathVariable String orderId, @RequestParam(value = "shopId") String shopId) throws ShopNotExist, OrderNotFoundException {
        Order shopOrder= shopOrderService.updateOrderStatus(orderId,shopId,"reject");
        kafkaService.sendMessageToCustomer(shopOrder);
        return new ResponseEntity<>(convertToOrderDto(shopOrder), HttpStatus.OK);
    }

    private OrderDto convertToOrderDto(Order order) {return modelMapper.map(order, OrderDto.class);}

    private List<OrderDto> convertToOrderDtoList(List<Order> orders) {return orders
            .stream()
            .map(order->modelMapper.map(order, OrderDto.class))
            .collect(Collectors.toList());
    }

}
