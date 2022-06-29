package com.Prograd.Springboot.Backend.Service;

import com.Prograd.Springboot.Backend.Modals.Customer;
import com.Prograd.Springboot.Backend.Modals.Order;
import com.Prograd.Springboot.Backend.Repository.CustomerRepository;
import com.Prograd.Springboot.Backend.Repository.OrderRepository;
import com.Prograd.Springboot.Backend.Repository.PlantRepository;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtAuthenticationFilter;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtTokenHelper;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;
import com.Prograd.Springboot.Backend.exceptions.OrderNotFound;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository OrderRepository;

    public OrderServiceImpl(OrderRepository OrderRepository) {
        this.OrderRepository = OrderRepository;
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtTokenHelper jwtTokenHelper;


    @Override
    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

    @Override
    public Order saveOrder(int customer_id,Order Order) throws CustomerNotFound, PlantNotFound {
        int plant_id = Order.getPlant_id();

        Customer customer = customerRepository.findById(customer_id).orElseThrow(()->new CustomerNotFound("Customer not exist"));

        String usernameFromDB = customer.getUsername();

        String jwt = jwtAuthenticationFilter.parseJwt(request);
        String username = jwtTokenHelper.getUserNameFromJwtToken(jwt);

        if(usernameFromDB.equals(username)) {
            if (plantRepository.existsById(plant_id)) {
                Order.setCustomer_id(customer_id);
                return OrderRepository.save(Order);
            } else {
                throw new PlantNotFound("Plant Not Found");
            }
        }else{
            throw new CustomerNotFound("You don't have access for this user");
        }
    }

    @Override
    public Order getOrderById(int id) throws OrderNotFound {
        Order Order = OrderRepository.findById(id).orElseThrow(()->new OrderNotFound("Order not exist"));
        return Order;
    }

    @Override
    public void deleteOrder(int id) throws OrderNotFound {
        if(OrderRepository.existsById(id)) {
            OrderRepository.deleteById(id);
        }else{
            throw new OrderNotFound("Order Not Found");
        }
    }

    @Override
    public List<Order> getOrderByCustomer(int customer_id) throws CustomerNotFound {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(()->new CustomerNotFound("Customer not exist"));

        String usernameFromDB = customer.getUsername();

        String jwt = jwtAuthenticationFilter.parseJwt(request);
        String username = jwtTokenHelper.getUserNameFromJwtToken(jwt);

        if(usernameFromDB.equals(username)) {
            return OrderRepository.findByCustomerId(customer_id);
        }else{
            throw new CustomerNotFound("You don't have access for this user");
        }
    }
}
