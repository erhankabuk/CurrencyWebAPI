package com.erhankabuk.CurrencyWebAPI.Service;

import com.erhankabuk.CurrencyWebAPI.DAO.Dao_CurrencyWebAPI;
import com.erhankabuk.CurrencyWebAPI.Repo.Repo_CurrencyWebAPI;
import com.erhankabuk.CurrencyWebAPI.Utility.BusinessIntegrityException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Service_CurrencyWebAPI {

    @Autowired
    Dao_CurrencyWebAPI daoCurrencyWebAPI;
    @Autowired
    Repo_CurrencyWebAPI repoCurrencyWebAPI;

    RestTemplate restTemplate = new RestTemplate();

    public List<LocalDate> getDate() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().minusMonths(1);
        List<LocalDate> dates = Stream.iterate(end, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(end, start.plusDays(1)))
                .collect(Collectors.toList());
        return dates;
    }

    public List<String> createDailyURL() {
        List<String> urlList = new ArrayList();
        getDate().stream().distinct().forEach(day-> {
            String dailyURL="https://api.exchangeratesapi.io/" + day + "?base=USD&symbols=TRY";
            urlList.add(dailyURL);
        });
        return urlList;
    }

    public void setDataToDatabase() throws BusinessIntegrityException {
        for (String url: createDailyURL()) {
            repoCurrencyWebAPI.setDailyCurrencyToDatabase( new JSONObject(restTemplate.getForObject(url,String.class)));
        }
    }

    public void deletePreviousDataFromDatabase() {
       repoCurrencyWebAPI.deleteDatabase();
    }

    public void checkDatabaseExist() throws BusinessIntegrityException {
        if(Objects.isNull(daoCurrencyWebAPI.findFirstByDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))){
            System.out.println("Database needs to be update");
            deletePreviousDataFromDatabase();
            setDataToDatabase();
        } getListFromDatabase();
     }

    public void getListFromDatabase() throws BusinessIntegrityException {
        System.out.println("Database is up to date as below...");
        repoCurrencyWebAPI.getDailyCurrencyFromDatabase().forEach(System.out::println);
    }

}
