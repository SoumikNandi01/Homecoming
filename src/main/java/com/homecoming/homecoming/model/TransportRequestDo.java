package com.homecoming.homecoming.model;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransportRequestDo {
    private String date;
    private String pickupLocation;
    private String dropLocation;
}
