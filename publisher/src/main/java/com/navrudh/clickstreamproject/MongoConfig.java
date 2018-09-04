package com.navrudh.clickstreamproject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "click";
  }

  @Override
  public MongoClient mongoClient() {
    ServerAddress server = new ServerAddress("mongo", 27017);
    MongoCredential credentials =
        MongoCredential.createCredential("root", "admin", "example".toCharArray());
    return new MongoClient(server, credentials, MongoClientOptions.builder().build());
  }
}
