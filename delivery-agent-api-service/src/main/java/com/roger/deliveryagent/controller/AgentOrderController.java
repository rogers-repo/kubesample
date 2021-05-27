package com.stackroute.deliveryagent.controller;

import com.stackroute.deliveryagent.domain.Order;
import com.stackroute.deliveryagent.dto.OrderDto;
import com.stackroute.deliveryagent.exception.OrderNotFound;
import com.stackroute.deliveryagent.repository.DeliveryAgentRepository;
import com.stackroute.deliveryagent.service.AgentOrderService;
import com.stackroute.deliveryagent.service.KafkaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/delivery/orders")
public class AgentOrderController {
    @Autowired
    private AgentOrderService agentOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaService kafkaService;

    /*
    Define a handler methods:
    GET /api/delivery/orders?agentId={agentId}
    PUT /api/delivery/orders/{orderId}/arrive?agentId={agentId}
    PUT /api/delivery/orders/{orderId}/pickup?agentId={agentId}
    PUT /api/delivery/orders/{orderId}/deliver?agentId={agentId}
    */

    @GetMapping("")
    @ApiOperation(value = "Get all orders for a delivery agent",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all orders for a delivery agent"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderDto>> getAllAgentOrders(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "per_page",defaultValue = "10") String per_page, @RequestParam(value = "page",defaultValue = "0") String page) throws OrderNotFound {
        List<Order> agentOrder= agentOrderService.getAllDeliveryOrders(agentId,per_page,page);
        return new ResponseEntity<>(convertToOrderDtoList(agentOrder), HttpStatus.OK);

    }

    private OrderDto convertToOrderDto(Order order) {return modelMapper.map(order, OrderDto.class);}

    private List<OrderDto> convertToOrderDtoList(List<Order> orders) {return orders
            .stream()
            .map(order->modelMapper.map(order, OrderDto.class))
            .collect(Collectors.toList());
    }

    @PutMapping("/{orderId}/arrive")
    @ApiOperation(value = "update customer that  order has arrived ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated customer that  order has arrived"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToArrived(@PathVariable String orderId, @RequestParam(value = "agentId") String agentId) throws OrderNotFound {
        Order AgentOrder= agentOrderService.updateOrderStatus(orderId,agentId,"arrive");
        kafkaService.sendMessageToCustomer(AgentOrder);
        return new ResponseEntity<>(convertToOrderDto(AgentOrder), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    @ApiOperation(value = "update order status to deliver ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated orders to delivered state"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToDeliver(@PathVariable String orderId, @RequestParam(value = "agentId") String agentId) throws OrderNotFound {
        Order AgentOrder= agentOrderService.updateOrderStatus(orderId,agentId,"deliver");
        kafkaService.sendMessageToCustomer(AgentOrder);
        return new ResponseEntity<>(convertToOrderDto(AgentOrder), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/pickup")
    @ApiOperation(value = "update order status to pickup ",response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated orders to pickup state"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatusToPickup(@PathVariable String orderId, @RequestParam(value = "agentId") String agentId) throws OrderNotFound {
        Order AgentOrder= agentOrderService.updateOrderStatus(orderId,agentId,"pickup");
        kafkaService.sendMessageToCustomer(AgentOrder);
        return new ResponseEntity<>(convertToOrderDto(AgentOrder), HttpStatus.OK);
    }
}
