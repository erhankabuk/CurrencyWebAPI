package com.erhankabuk.CurrencyWebAPI.Repo;

import com.erhankabuk.CurrencyWebAPI.DAO.Dao_CurrencyWebAPI;
import com.erhankabuk.CurrencyWebAPI.Model.Rates;
import com.erhankabuk.CurrencyWebAPI.Model.Root;
import com.erhankabuk.CurrencyWebAPI.Utility.BusinessIntegrityException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Repo_CurrencyWebAPI extends Root{

    @Autowired
    Dao_CurrencyWebAPI daoCurrencyWebAPI;

    public void setDailyCurrencyToDatabase(JSONObject day) throws BusinessIntegrityException {
        try{
        Root root = new Root();// modelden obje oluşturmak gerekiyor.
        root.setDate(new SimpleDateFormat("dd/MM/yyyy")
                .format(new SimpleDateFormat("yyyy-MM-dd").parse(day.getString("date"))));
        root.setBase(day.getString("base"));
        root.setRates(new Rates(day.getJSONObject("rates").getDouble("TRY")));
        daoCurrencyWebAPI.save(root);//dao.save.obje yapmadan database insert yapmıyor
    } catch (Exception e) { throw new BusinessIntegrityException("Error in setDailyCurrencyToDatabase in Repo...");
    }
    }

    public List<String> getDailyCurrencyFromDatabase() throws BusinessIntegrityException {
        try{
            List<Root> currencyListInDatabase = (List<Root>) daoCurrencyWebAPI.findAll();
            List<String> currencListFromDatabase = new ArrayList<>();
            for (Root currency:currencyListInDatabase ) {
                String obje ="{\"date\":\""+ currency.getDate() +"\",\"rates\":{\"TRY\":"+currency.getRates().gettRY()+"},\"base\":\""+currency.getBase()+"\"}" ;
                currencListFromDatabase.add(obje);
            }
            return currencListFromDatabase;
        } catch (Exception e) { throw new BusinessIntegrityException("Error in getDailyCurrencyFromDatabase in Repo...");
        }
    }

    public void deleteDatabase(){
        daoCurrencyWebAPI.deleteAll();
    }

}
