package com.Prograd.Springboot.Backend.Service;

import com.Prograd.Springboot.Backend.Modals.Customer;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer Customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id) throws Exception;
    Customer adminGetCustomerById(int id) throws CustomerNotFound;
    Customer updateCustomer(Customer Customer,int id) throws CustomerNotFound;
    Customer adminUpdateCustomer(Customer Customer,int id) throws CustomerNotFound;
    void deleteCustomer(int id);
}
