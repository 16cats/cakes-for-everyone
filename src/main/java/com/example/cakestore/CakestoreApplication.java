package com.example.cakestore;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cakestore.domain.AppUser;
import com.example.cakestore.domain.AppUserRepository;
import com.example.cakestore.domain.Cake;
import com.example.cakestore.domain.CakeRepository;
import com.example.cakestore.domain.Category;
import com.example.cakestore.domain.CategoryRepository;

@SpringBootApplication
public class CakestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakestoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CakeRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
	    return (args) -> {
	    	
	    	crepository.save(new Category("Chocolate"));
	    	crepository.save(new Category("Fruit"));
	    	crepository.save(new Category("Savory"));

			repository.save(new Cake("Chocolate cake","G, L","Plain classic chocolate cake.",29.95,crepository.findByName("Chocolate").get(0)));
			repository.save(new Cake("Strawberry cake","G, L, VS","Plain classic Strawberry cake.",29.95,crepository.findByName("Fruit").get(0)));
			repository.save(new Cake("Sandwich cake","L","Plain classic Sandwich cake.",29.95,crepository.findByName("Savory").get(0)));

        //	AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		//	AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		//	urepository.save(user1);
		//	urepository.save(user2);
			
		//	log.info("fetch all users");
		//	for (AppUser appuser : urepository.findAll()) {
		//		log.info(appuser.toString());
		//	}
	    };
	}
}