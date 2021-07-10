package com.homecoming.homecoming.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homecoming.homecoming.containerdao.TransportDataContainerDao;
import com.homecoming.homecoming.model.TransportMode;
import com.homecoming.homecoming.model.TransportRequestDo;
import com.homecoming.homecoming.model.rest.GetTransportOptionsResponse;
import com.homecoming.homecoming.utils.JsonReader;
import com.homecoming.homecoming.validators.activity.GetTravelOptionsValidator;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_DATE;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_DROP;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_DROP_TIME;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_PICKUP;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_PICKUP_TIME;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_SEATS_AVAILABLE;
import static com.homecoming.homecoming.TestConstants.TransportDocumentConstants.TEST_VEHICLE_NUMBER;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.DATE;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.DROP_LOCATION;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.DROP_TIME;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.PICKUP_LOCATION;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.PICKUP_TIME;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.SEATS_AVAILABLE;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.TRANSPORT_MODE;
import static com.homecoming.homecoming.constants.Constants.TransportRequestFieldConstants.VEHICLE_NUMBER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetTravelOptionsActivityTest extends AbstractTest {
    @Mock
    private TransportDataContainerDao transportDataContainerDao;

    @InjectMocks
    private GetTravelOptionsActivity getTravelOptionsActivity;

    private JsonReader jsonReader;
    private String testDirectory;
    private ObjectMapper objectMapper;
    private GetTravelOptionsValidator getTravelOptionsValidator;

    @BeforeEach
    public void startup() {
        objectMapper = new ObjectMapper();
        jsonReader = new JsonReader();
        testDirectory = "gettraveloptions/";
        getTravelOptionsValidator = new GetTravelOptionsValidator();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void happyCase_gettingTravelOptions() throws IOException {
        when(transportDataContainerDao.getTransportData(any(TransportRequestDo.class)))
                .thenReturn(Collections.singletonList(getSampleTransportDocument()));
        GetTransportOptionsResponse actual =
                getTravelOptionsActivity.getTravelOptions(TEST_DATE, TEST_PICKUP, TEST_DROP);
        GetTransportOptionsResponse expected = getTransportOptionsResponse("happyCase.json");
        getTravelOptionsValidator.validateGetTravelOptionsResponse(actual, expected);
    }

    @Test
    public void happyCase_gettingNoTravelOptions() throws IOException {
        when(transportDataContainerDao.getTransportData(any(TransportRequestDo.class)))
                .thenReturn(new ArrayList<>());
        GetTransportOptionsResponse actual =
                getTravelOptionsActivity.getTravelOptions(TEST_DATE, TEST_PICKUP, TEST_DROP);
        GetTransportOptionsResponse expected = getTransportOptionsResponse("emptyTravelOptions.json");
        getTravelOptionsValidator.validateGetTravelOptionsResponse(actual, expected);
    }


    private Document getSampleTransportDocument() {
        Document document = new Document();
        document.put(DATE, TEST_DATE);
        document.put(PICKUP_LOCATION, TEST_PICKUP);
        document.put(DROP_LOCATION, TEST_DROP);
        document.put(TRANSPORT_MODE, TransportMode.BUS.name());
        document.put(VEHICLE_NUMBER, TEST_VEHICLE_NUMBER);
        document.put(PICKUP_TIME, TEST_PICKUP_TIME);
        document.put(DROP_TIME, TEST_DROP_TIME);
        document.put(SEATS_AVAILABLE, TEST_SEATS_AVAILABLE);

        return document;
    }

    private GetTransportOptionsResponse getTransportOptionsResponse(String fileName) throws IOException {
        return objectMapper.readValue(jsonReader.getJsonReaderFromFile(
                baseDirectory + testDirectory + fileName), GetTransportOptionsResponse.class);
    }
}
