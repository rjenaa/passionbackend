package com.batch.businessbatch.repository;

import com.batch.businessbatch.models.BusinessAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends CrudRepository<BusinessAccount, Long> {

    List<BusinessAccount> findAll();
}
