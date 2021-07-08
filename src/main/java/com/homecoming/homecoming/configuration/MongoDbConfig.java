package com.homecoming.homecoming.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.homecoming.homecoming.constants.Constants.UserDataContainerConstants.USER_DATA_COLLECTION;
import static com.homecoming.homecoming.constants.Constants.UserDataContainerConstants.USER_DATA_CONTAINER;

@Configuration
public class MongoDbConfig {

    @Bean
    public MongoCollection<Document> documentMongoCollection() {
        ConnectionString connectionString = new ConnectionString("mongodb://soumik:soumikn@cluster0-shard-00-00.nbzad.mongodb.net:27017,cluster0-shard-00-01.nbzad.mongodb.net:27017,cluster0-shard-00-02.nbzad.mongodb.net:27017/UserDataContainer?ssl=true&replicaSet=atlas-c2t9su-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase(USER_DATA_CONTAINER);
        return database.getCollection(USER_DATA_COLLECTION);
    }
}
