package com.abycuela.magica.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
@EnableReactiveMongoRepositories
public class MongoConfigReactive extends AbstractReactiveMongoConfiguration {
	//https://mongodb.github.io/mongo-java-driver/4.2/driver-reactive/tutorials/databases-collections/
	@Primary
	@Bean
	public MongoClient mongoclient() {
		MongoClient mongoClient = MongoClients.create();
		mongoClient.getDatabase(getDatabaseName());
		return mongoClient;
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "AbycuelaMagica";
	}
	
	 
	

}
