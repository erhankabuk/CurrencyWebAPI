package com.erhankabuk.CurrencyWebAPI;

import com.erhankabuk.CurrencyWebAPI.Repo.Repo_CurrencyWebAPI;
import com.erhankabuk.CurrencyWebAPI.Service.Service_CurrencyWebAPI;
import com.erhankabuk.CurrencyWebAPI.Utility.BusinessIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Configuration
@EnableScheduling
public class CurrencyWebApiApplication {

	@Autowired
	Service_CurrencyWebAPI serviceCurrencyWebAPI;
	@Autowired
	Repo_CurrencyWebAPI repoCurrencyWebAPI;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyWebApiApplication.class, args);
	}//.close()

	//@Scheduled(cron = "0 0 16 ? * *", zone="Europe/Paris") // Update time 16:00 CET
	@Scheduled(cron = "0 25 10 ? * *")//Run at LocalTime as 10:25:00 etc.
	void getDataFromDatabase() throws BusinessIntegrityException {
			serviceCurrencyWebAPI.checkDatabaseExist();
			System.exit(1);
	}

}
