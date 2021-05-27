package com.stackroute.flippy.service;

import com.stackroute.flippy.domain.Customer;
import com.stackroute.flippy.domain.Order;
import com.stackroute.flippy.exception.CustomerNotFoundException;
import com.stackroute.flippy.exception.OrderExistsException;
import com.stackroute.flippy.exception.OrderNotFoundException;
import com.stackroute.flippy.repository.CustomerOrderRepository;
import com.stackroute.flippy.security.model.AuthenticatedUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order addNewOrder(Order order) throws OrderExistsException, CustomerNotFoundException {
       Order ordertoBeSaved= setCustomerDetails(order);
        if (customerOrderRepository.findById(order.getOrderId()).isPresent()) {
            log.debug("order already exist " + order.getOrderId());
            throw new OrderExistsException();
        }
        return customerOrderRepository.save(ordertoBeSaved);
    }

    @Override
    public Order getOrderByOrderId(String orderId) throws OrderNotFoundException {
        return customerOrderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order getOrderByOrderIdAndCustomerId(String orderId, String customerID) throws OrderNotFoundException {
        return customerOrderRepository.findOrderByOrderIdAndCustomerCustomerId(orderId,customerID).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> getAllOrderByCustomerId(String customerId,String per_page,String page) throws CustomerNotFoundException {
      /* Optional<List<Order>>orders= customerOrderRepository.findByCustomerCustomerId(customerId);
       if(orders.get().isEmpty())
       {
           throw new CustomerNotFoundException();
       }
       return orders.get();*/
        Page<Order> orders= customerOrderRepository.findByCustomerCustomerId(customerId, PageRequest.of(Integer.parseInt(page), Integer.parseInt(per_page)));
        if(!orders.hasContent())
        {
            throw new CustomerNotFoundException();
        }
        return orders.getContent();
    }

    private Order setCustomerDetails(Order order) throws CustomerNotFoundException {
        Customer customer=new Customer();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof AuthenticatedUser) {
            customer.setCustomerId(((AuthenticatedUser) principal).getId().toString());
            customer.setAddress(((AuthenticatedUser)principal).getAddress());
            customer.setName(((AuthenticatedUser)principal).getUsername());
        } else {
            log.error("customer not found ");
            throw new CustomerNotFoundException();
        }

        Order orderWithCustomer= modelMapper.map(order,Order.class);
        orderWithCustomer.setCustomer(customer);
        orderWithCustomer.setStatus("created");
        return orderWithCustomer;

    }
}
