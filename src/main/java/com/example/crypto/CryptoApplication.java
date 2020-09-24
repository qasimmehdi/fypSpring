package com.example.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CryptoApplication {


	public static void main(String[] args) {

/*		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "admin");
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is("Shazad2"));
		List<UserModel> users = mongoOps.find(query, UserModel.class);
		System.out.println(users);*/
		SpringApplication.run(CryptoApplication.class, args);
	}

}
