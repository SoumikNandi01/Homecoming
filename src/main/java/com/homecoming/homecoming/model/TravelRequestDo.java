package com.homecoming.homecoming.model;
import lombok.Data;

@Data
public class TravelRequestDo {
    private long date;
    private String pickupLocation;
    private String dropLocation;
}
