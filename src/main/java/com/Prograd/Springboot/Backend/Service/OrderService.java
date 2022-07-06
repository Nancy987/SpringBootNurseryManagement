package com.Prograd.Springboot.Backend.Service;
import com.Prograd.Springboot.Backend.Modals.Order;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;
import com.Prograd.Springboot.Backend.exceptions.OrderNotFound;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;

import java.util.List;

public interface OrderService {
    Order saveOrder(int id,Order Order) throws CustomerNotFound, PlantNotFound;
    List<Order> getOrderByCustomer(int id) throws CustomerNotFound;
    List<Order> getAllOrders();
    Order updateOrder(Order order, int id) throws OrderNotFound, PlantNotFound;
    Order getOrderById(int id) throws OrderNotFound;
    Order getOneOrderByCustomerId(int customer_id, int order_id) throws OrderNotFound, CustomerNotFound;
    void deleteOrder(int id) throws OrderNotFound;
}
