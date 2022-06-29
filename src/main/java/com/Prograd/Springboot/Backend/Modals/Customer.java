package com.Prograd.Springboot.Backend.Modals;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="Customers")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int customer_id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String customer_name;
    @Column(nullable = false)
    private String email_id;
    @Column(nullable = false)
    private String phone_no;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role = "ROLE_CUSTOMER";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(getRole());

        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
