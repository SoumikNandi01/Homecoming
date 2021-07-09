package com.homecoming.homecoming.model.rest;

import com.homecoming.homecoming.model.TransportOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetTransportOptionsResponse {
   List<TransportOption> transportOptionsList;
}
