package com.Prograd.Springboot.Backend.Modals;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int order_id;
    @Column(nullable = false)
    private int customer_id;
    @Column(nullable = false)
    @NotEmpty
    private int plant_id;
    @Column(nullable = false)
    private String order_date;
    @Column(nullable = false)
    private String order_time;
    @Column(nullable = false)
    @NotEmpty
    private int quantity;
    @Column(nullable = false)
    private float total_cost;

    public Order() {
        Date date = new Date();
        String strDateFormat = "dd-MM-Y";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        this.order_date = formattedDate;

        String strTimeFormat = "hh:mm a";
        DateFormat timeFormat = new SimpleDateFormat(strTimeFormat);
        String formattedTime = timeFormat.format(date);
        this.order_time = formattedTime;
    }
}
