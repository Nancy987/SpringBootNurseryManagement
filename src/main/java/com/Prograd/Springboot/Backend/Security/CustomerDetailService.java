package com.Prograd.Springboot.Backend.Security;

import com.Prograd.Springboot.Backend.Modals.Customer;
import com.Prograd.Springboot.Backend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {            // authentication with database
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loads user from database by username
        Customer customer = this.customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return customer;
    }
}