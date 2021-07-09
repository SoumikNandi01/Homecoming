package com.homecoming.homecoming.validators.activity;

import com.homecoming.homecoming.model.TransportOption;
import com.homecoming.homecoming.model.rest.GetTransportOptionsResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetTravelOptionsValidator {

    public void validateGetTravelOptionsResponse(GetTransportOptionsResponse actualResponse,
                                                 GetTransportOptionsResponse expectedResponse) {
        if (actualResponse.getTransportOptionsList().isEmpty() && expectedResponse.getTransportOptionsList().isEmpty()) {
            return;
        }

        List<TransportOption> actualResponseList = actualResponse.getTransportOptionsList();
        List<TransportOption> expectedResponseList = expectedResponse.getTransportOptionsList();
        assertEquals(actualResponseList.size(), expectedResponseList.size());

        for (int i = 0; i < actualResponseList.size(); i++) {
            validateTransportOption(actualResponseList.get(i), expectedResponseList.get(i));
        }
    }

    private void validateTransportOption(TransportOption actualResponse, TransportOption expectedResponse) {
        assertEquals(actualResponse.getDate(), expectedResponse.getDate());
        assertEquals(actualResponse.getDropLocation(), expectedResponse.getDropLocation());
        assertEquals(actualResponse.getDropTime(), expectedResponse.getDropTime());
        assertEquals(actualResponse.getPickupLocation(), expectedResponse.getPickupLocation());
        assertEquals(actualResponse.getPickupTime(), expectedResponse.getPickupTime());
        assertEquals(actualResponse.getSeatsAvailable(), expectedResponse.getSeatsAvailable());
        assertEquals(actualResponse.getTransportMode(), expectedResponse.getTransportMode());
        assertEquals(actualResponse.getVehicleNumber(), expectedResponse.getVehicleNumber());
    }
}
