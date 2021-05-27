package com.stackroute.flippy.controller;

import com.stackroute.flippy.domain.Order;
import com.stackroute.flippy.dto.OrderDto;
import com.stackroute.flippy.exception.CustomerNotFoundException;
import com.stackroute.flippy.exception.OrderExistsException;
import com.stackroute.flippy.exception.OrderNotFoundException;
import com.stackroute.flippy.service.CustomerOrderService;
import com.stackroute.flippy.service.KafkaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/orders")
@Api(value = "Flippy Api",tags = {"FlippyAPI"})
@SwaggerDefinition(tags={@Tag(name="FlippyAPI",description = "Save and gets customer order")})
@Slf4j
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService orderService;

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private KafkaService kafkaService;

    /*
    Define a handler methods:
    POST /api/orders
    GET /api/orders?customerId={customerId}
    GET /api/orders/{orderId}?customerId={customerId}
    */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save customer order",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Successfully created the order"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    public OrderDto addNewOrder(@RequestBody Order order) throws OrderExistsException, CustomerNotFoundException {
        Order newOrder = orderService.addNewOrder(order);
        OrderDto newOrderDto = convertToOrderDto(newOrder);
        log.info("Order has been created "+ newOrder);
        kafkaService.sendOrders(newOrder);
        return newOrderDto;
    }

    @GetMapping()
    @ApiOperation(value = "Get all customer orders",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer orders"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    public List<OrderDto> getAllOrdersByCustomerId(@RequestParam(value="customerid") String customerId, @RequestParam(value = "per_page",defaultValue = "10") String per_page, @RequestParam(value = "page",defaultValue = "0") String page) throws CustomerNotFoundException {
        log.debug("getting All Orders By CustomerId : " + customerId);
        return convertToOrderDtoList(orderService.getAllOrderByCustomerId(customerId,per_page,page));
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Get customer order based on orderId",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer orders"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    public OrderDto getOrderByIdAndCustomerId(@PathVariable String orderId,@RequestParam(value="customerid") String customerId) throws OrderNotFoundException {
        return convertToOrderDto(orderService.getOrderByOrderIdAndCustomerId(orderId,customerId));
    }


    private OrderDto convertToOrderDto(Order order) {return modelMapper.map(order, OrderDto.class);}

    private List<OrderDto> convertToOrderDtoList(List<Order> orders) {return orders
            .stream()
            .map(order->modelMapper.map(order, OrderDto.class))
            .collect(Collectors.toList());
    }

}
