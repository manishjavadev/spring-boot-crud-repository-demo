package com.manish.javadev.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manish.javadev.model.AccountEntity;

/**
 * @author Manish
 *
 */
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

}