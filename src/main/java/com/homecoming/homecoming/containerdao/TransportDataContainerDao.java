package com.homecoming.homecoming.containerdao;

import com.homecoming.homecoming.model.TransportRequestDo;
import org.bson.Document;

import java.util.List;

public interface TransportDataContainerDao {
    List<Document> getTransportData(TransportRequestDo transportRequestDo);
}
