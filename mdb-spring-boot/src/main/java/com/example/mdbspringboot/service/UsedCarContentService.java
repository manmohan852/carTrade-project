package com.example.mdbspringboot.service;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import com.example.mdbspringboot.repository.UsedCarRepository;
import com.example.mdbspringboot.repository.UsedCarRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsedCarContentService {

    @Autowired
    UsedCarRepository usedCarRepository;
    @Autowired
    UsedCarRepositoryDao usedCarRepositoryDao;

    public List<UsedCarEntity> getCarAllDetails() {
        return usedCarRepository.findAll();
    }

    public List<UsedCarEntity> getCarDetails(Map<String, String> reqParam) {
        Query query = new Query();
        for (String key : reqParam.keySet())
        {
            Criteria criteria = Criteria.where(key).is(reqParam.get(key));
            query.addCriteria(criteria);
        }
        return usedCarRepositoryDao.findUsedCarBasedOnCriteria(query);
    }

    public List<UsedCarEntity> getCarDetailsWithQuery(Map<String, String> reqParam) {
        Query query = new Query();
        String colorFilter = reqParam.get("color");
        Integer startAmount = Integer.parseInt(reqParam.get("carPriceStart"));
        Integer endAmount = Integer.parseInt(reqParam.get("carPriceEnd"));
        Criteria colorCriteria = Criteria.where("color").is(colorFilter);
        query.addCriteria(colorCriteria);
        Criteria startAmountCriteria = Criteria.where("marketPrice").gt(startAmount).lt(endAmount);
        query.addCriteria(startAmountCriteria);
        return usedCarRepositoryDao.findUsedCarBasedOnCriteria(query);
    }

    public List<UsedCarEntity> getCarDetailsWithBrand(Map<String, String> reqParam){
        Query query = new Query();
        String brandFilter = reqParam.get("brandName");
        Integer startYear = Integer.parseInt(reqParam.get("startYear"));
        Integer endYear = Integer.parseInt(reqParam.get("endYear"));
        String cityName = reqParam.get("cityName");
        Criteria brandCriteria = Criteria.where("makeName").is(brandFilter);
        query.addCriteria(brandCriteria);
        Criteria yearCriteria = Criteria.where("carYear").gte(startYear).lte(endYear);
        query.addCriteria(yearCriteria);
        Criteria cityCriteria = Criteria.where("cityName").is(cityName);
        query.addCriteria(cityCriteria);
        return usedCarRepositoryDao.findUsedCarBasedOnCriteria(query);
    }
}
