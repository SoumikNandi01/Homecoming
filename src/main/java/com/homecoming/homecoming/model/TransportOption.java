package com.homecoming.homecoming.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransportOption {
    private String date;
    private String pickupLocation;
    private String dropLocation;
    private TransportMode transportMode;
    private long vehicleNumber;
    private String pickupTime;
    private String dropTime;
    private int seatsAvailable;
}
