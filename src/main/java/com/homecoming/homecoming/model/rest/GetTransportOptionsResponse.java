package com.homecoming.homecoming.model.rest;

import com.homecoming.homecoming.model.TransportOption;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class GetTransportOptionsResponse {
   List<TransportOption> transportOptionsList;
}
