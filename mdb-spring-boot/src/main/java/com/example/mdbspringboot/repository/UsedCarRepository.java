package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsedCarRepository extends MongoRepository<UsedCarEntity, String> {


}
