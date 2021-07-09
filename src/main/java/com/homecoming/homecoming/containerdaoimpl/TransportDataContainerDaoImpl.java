package com.homecoming.homecoming.containerdaoimpl;

import com.homecoming.homecoming.containerdao.TransportDataContainerDao;
import com.homecoming.homecoming.model.TransportRequestDo;
import com.homecoming.homecoming.utils.DocumentBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransportDataContainerDaoImpl implements TransportDataContainerDao {

    private final MongoCollection<Document> transportDocumentMongoCollection;
    private final DocumentBuilder documentBuilder;
    @Override
    public List<Document> getTransportData(TransportRequestDo transportRequestDo) {
        List<Document> results = new ArrayList<>();
        FindIterable<Document> documentFindIterable =
                transportDocumentMongoCollection.find(documentBuilder
                .getDocumentFromTransportRequestDo(transportRequestDo));

        for (Document document : documentFindIterable) {
            results.add(document);
        }

        return results;
    }
}
