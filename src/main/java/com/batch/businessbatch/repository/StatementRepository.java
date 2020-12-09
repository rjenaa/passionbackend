package com.batch.businessbatch.repository;

import com.batch.businessbatch.models.Statement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends CrudRepository<Statement, Long> {

    List<Statement> findAll();

    List<Statement> findStatementByAccountId(Long Id);
}
