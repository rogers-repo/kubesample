package com.stackroute.shoppartner.service;

import com.stackroute.shoppartner.domain.Order;
import com.stackroute.shoppartner.domain.ShopPartner;
import com.stackroute.shoppartner.exception.OrderNotFoundException;
import com.stackroute.shoppartner.exception.ShopNotExist;
import com.stackroute.shoppartner.repository.ShopOrderRepository;
import com.stackroute.shoppartner.security.model.AuthenticatedUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Order> getAllShopOrders(String shopId,String per_page,String page) throws ShopNotExist {
       Page<Order> orders= shopOrderRepository.findByShopPartnerShopPartnerid(shopId, PageRequest.of(Integer.parseInt(page), Integer.parseInt(per_page)));
        if(!orders.hasContent())
        {
            throw new ShopNotExist();
        }
        return orders.getContent();
    }

    @Override
    public Order getOrderByIdAndShopId(String orderId, String shopId) throws ShopNotExist {
        return shopOrderRepository.findOrderByOrderIdAndShopPartnerShopPartnerid(orderId,shopId).orElseThrow(ShopNotExist::new);
    }

    @Override
    public Order updateOrderStatus(String orderId, String shopId,String status) throws OrderNotFoundException, ShopNotExist {
        if(getLoggedInShopPartnerDetails().getShopPartnerid().equals(shopId)) {
            Order shopOrder=  shopOrderRepository.findOrderByOrderIdAndShopPartnerShopPartnerid(orderId, shopId).orElseThrow(OrderNotFoundException::new);
            Order order= modelMapper.map(shopOrder,Order.class);
            order.getShopPartner().setStatus(status);
            return shopOrderRepository.save(order);
        }
        else throw new ShopNotExist();
    }

    private ShopPartner getLoggedInShopPartnerDetails() throws ShopNotExist {
        ShopPartner shop=new ShopPartner();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof AuthenticatedUser) {
            shop.setShopPartnerid(((AuthenticatedUser) principal).getId().toString());
            shop.setAddress(((AuthenticatedUser)principal).getAddress());
            shop.setName(((AuthenticatedUser)principal).getUsername());
        } else {
            log.error("customer not found ");
            throw new ShopNotExist();
        }
        return shop;
    }
}
