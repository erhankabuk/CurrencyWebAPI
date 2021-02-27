package com.erhankabuk.CurrencyWebAPI.DAO;

import com.erhankabuk.CurrencyWebAPI.Model.Root;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dao_CurrencyWebAPI extends CrudRepository<Root, Long> {

    @Override
    Iterable<Root> findAll();
    Object findFirstByDate(String format);

}
