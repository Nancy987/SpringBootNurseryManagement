package com.Prograd.Springboot.Backend.Controllers;

import com.Prograd.Springboot.Backend.Modals.Order;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtTokenHelper;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;
import com.Prograd.Springboot.Backend.exceptions.OrderNotFound;
import com.Prograd.Springboot.Backend.Service.OrderService;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("")
public class OrderController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    private OrderService OrderService;

    public OrderController(OrderService OrderService) {
        this.OrderService = OrderService;
    }

    @PostMapping("customer/{id}/order/create")
    public ResponseEntity<Order> saveOrder(@PathVariable("id") int id,@RequestBody Order Order) throws CustomerNotFound, PlantNotFound {
        return new ResponseEntity<Order>(OrderService.saveOrder(id,Order), HttpStatus.CREATED);
    }

    @GetMapping("admin/orders")
    public List<Order> getAllOrders(){
        return OrderService.getAllOrders();
    }
    @GetMapping("admin/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) throws OrderNotFound {
        return new ResponseEntity<Order>(OrderService.getOrderById(id), HttpStatus.OK);
    }
    @DeleteMapping("admin/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) throws OrderNotFound {
        OrderService.deleteOrder(id);
        return new ResponseEntity<String>("Order is deleted successfully.",HttpStatus.OK);
    }

    @GetMapping("customer/{id}/orders")
    public List<Order> getOrderByCustomer(@PathVariable("id") int id) throws CustomerNotFound {
        return OrderService.getOrderByCustomer(id);
    }
}
