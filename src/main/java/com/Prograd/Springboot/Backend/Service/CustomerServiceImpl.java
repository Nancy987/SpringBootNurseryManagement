package com.Prograd.Springboot.Backend.Service;

import com.Prograd.Springboot.Backend.Modals.Customer;
import com.Prograd.Springboot.Backend.Repository.CustomerRepository;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtAuthenticationFilter;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtTokenHelper;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findByRole("ROLE_CUSTOMER");
    }

    @Override
    public Customer getCustomerById(int id) throws CustomerNotFound {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound("Customer not exist"));

        String usernameFromDB = customer.getUsername();
        String jwt = jwtAuthenticationFilter.parseJwt(request);
        String username = jwtTokenHelper.getUserNameFromJwtToken(jwt);

        if(usernameFromDB.equals(username)){
            return customer;
        }else{
            throw new CustomerNotFound("You don't have access for this user");
        }
    }

    @Override
    public Customer adminGetCustomerById(int id) throws CustomerNotFound {
        Customer Customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound("Customer not exist"));
        return Customer;
    }

    @Override
    public Customer updateCustomer(Customer customer, int id) throws CustomerNotFound {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound(("Customer not exist")));

        String usernameFromDB = customer.getUsername();
        String jwt = jwtAuthenticationFilter.parseJwt(request);
        String username = jwtTokenHelper.getUserNameFromJwtToken(jwt);

        if(usernameFromDB.equals(username)) {
            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            existingCustomer.setCustomer_name(customer.getCustomer_name());
            existingCustomer.setUsername(customer.getUsername());
            existingCustomer.setEmail_id(customer.getEmail_id());
            existingCustomer.setPhone_no(customer.getPhone_no());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setRole("ROLE_CUSTOMER");
            existingCustomer.setPassword(encodedPassword);

            return customerRepository.save(existingCustomer);
        }else{
            throw new CustomerNotFound("You don't have access for this user");
        }
    }

    @Override
    public Customer adminUpdateCustomer(Customer customer, int id) throws CustomerNotFound {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound(("Customer not exist")));

        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        existingCustomer.setCustomer_name(customer.getCustomer_name());
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setEmail_id(customer.getEmail_id());
        existingCustomer.setPhone_no(customer.getPhone_no());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setRole("ROLE_CUSTOMER");
        existingCustomer.setPassword(encodedPassword);

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
