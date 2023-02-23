package nus.iss.ws13.workshop13.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class ContactConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Value("${spring.data.mongodb.database}")
    private String dbName;
    
    @Bean
    public MongoTemplate createMongoTemplate() {
        // create mongo client
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, dbName);
    }
}
