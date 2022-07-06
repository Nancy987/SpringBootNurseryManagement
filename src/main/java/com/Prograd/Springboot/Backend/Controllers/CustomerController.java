package com.Prograd.Springboot.Backend.Controllers;

import com.Prograd.Springboot.Backend.Modals.Customer;
import com.Prograd.Springboot.Backend.Payload.Response.JwtAuthResponse;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtTokenHelper;
import com.Prograd.Springboot.Backend.exceptions.CustomerNotFound;
import com.Prograd.Springboot.Backend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    private CustomerService CustomerService;

    public CustomerController(CustomerService CustomerService) {
        this.CustomerService = CustomerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer Customer) throws Exception {

        return new ResponseEntity<Customer>(CustomerService.saveCustomer(Customer), HttpStatus.CREATED);
    }

    @GetMapping("admin/allCustomers")
    public List<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<Customer>(CustomerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("admin/customer/{id}")
    public ResponseEntity<Customer> adminGetCustomerById(@PathVariable("id") int id) throws CustomerNotFound {
        return new ResponseEntity<Customer>(CustomerService.adminGetCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@Valid @RequestBody Customer Customer) throws CustomerNotFound {
        return new ResponseEntity<Customer>(CustomerService.updateCustomer(Customer,id), HttpStatus.OK);
    }

    @PutMapping("admin/customer/{id}")
    public ResponseEntity<Customer> adminUpdateCustomer(@PathVariable("id") int id,@Valid @RequestBody Customer Customer) throws CustomerNotFound {
        return new ResponseEntity<Customer>(CustomerService.adminUpdateCustomer(Customer,id), HttpStatus.OK);
    }
    @DeleteMapping("admin/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){
        CustomerService.deleteCustomer(id);
        return new ResponseEntity<String>("Customer is deleted successfully.",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Customer Customer) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(Customer.getUsername(), Customer.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenHelper.generateJwtToken(authentication);

        Customer userDetails = (Customer) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtAuthResponse(jwt,
                (long) userDetails.getCustomer_id(),
                userDetails.getUsername(),
                userDetails.getEmail_id(),
                roles));
    }
}
