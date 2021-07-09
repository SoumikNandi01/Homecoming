package com.homecoming.homecoming.activity;

import com.homecoming.homecoming.containerdao.TransportDataContainerDao;
import com.homecoming.homecoming.model.TransportMode;
import com.homecoming.homecoming.model.TransportOption;
import com.homecoming.homecoming.model.TransportRequestDo;
import com.homecoming.homecoming.model.rest.GetTransportOptionsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.*;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetTravelOptionsActivity {
    private final TransportDataContainerDao transportDataContainerDao;

    @GetMapping("/traveloptions")
    public GetTransportOptionsResponse getTravelOptions(@RequestParam String date,
                                                        @RequestParam String pickup,
                                                        @RequestParam String drop) {
        List<TransportOption> transportOptionList = new ArrayList<>();
        List<Document> transportOptions = transportDataContainerDao
                .getTransportData(TransportRequestDo.builder()
                        .date(date)
                        .dropLocation(drop)
                        .pickupLocation(pickup)
                        .build());

        transportOptions.forEach(transportOption -> transportOptionList.add(
                TransportOption.builder()
                        .seatsAvailable(transportOption.getInteger(SEATS_AVAILABLE))
                        .dropTime(transportOption.getString(DROP_TIME))
                        .dropLocation(transportOption.getString(DROP_LOCATION))
                        .date(transportOption.getString(DATE))
                        .pickupLocation(transportOption.getString(PICKUP_LOCATION))
                        .pickupTime(transportOption.getString(PICKUP_TIME))
                        .transportMode(TransportMode.valueOf(transportOption.getString(TRANSPORT_MODE)))
                        .vehicleNumber(transportOption.getLong(VEHICLE_NUMBER))
                        .build()
        ));

        return GetTransportOptionsResponse.builder()
                .transportOptionsList(transportOptionList)
                .build();
    }
}
