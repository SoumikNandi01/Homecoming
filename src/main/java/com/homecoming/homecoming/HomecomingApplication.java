package com.homecoming.homecoming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homecoming.homecoming.activity.GetTravelOptionsActivity;
import com.homecoming.homecoming.model.TransportMode;
import com.homecoming.homecoming.model.TransportOption;
import com.homecoming.homecoming.model.rest.GetTransportOptionsResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class HomecomingApplication {

	public static void main(String[] args) throws JsonProcessingException {
		TransportOption transportOption = TransportOption.builder()
				.vehicleNumber(123L)
				.transportMode(TransportMode.BUS)
				.build();
		List<TransportOption> list = new ArrayList<>();
		list.add(transportOption);
		GetTransportOptionsResponse getTransportOptionsResponse =
				GetTransportOptionsResponse.builder()
						.transportOptionsList(list)
						.build();

		String jsonOb = new Gson().toJson(getTransportOptionsResponse);
		System.out.println(jsonOb);
		GetTransportOptionsResponse itemWithOwner = new ObjectMapper().readValue(jsonOb, GetTransportOptionsResponse.class);
		System.out.println(itemWithOwner.getTransportOptionsList().get(0).getVehicleNumber()+ "<---This");
		SpringApplication.run(HomecomingApplication.class, args);
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
