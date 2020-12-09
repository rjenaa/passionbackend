package com.batch.businessbatch.repository;

import com.batch.businessbatch.models.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sales, Long> {

    List<Sales> findAll();
}
