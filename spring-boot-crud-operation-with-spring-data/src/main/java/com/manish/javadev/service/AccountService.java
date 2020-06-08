package com.manish.javadev.service;

import java.util.List;
import java.util.Map;

import com.manish.javadev.model.AccountEntity;

public interface AccountService {

	AccountEntity save(AccountEntity accountEntity);

	Iterable<AccountEntity> saveAll(List<AccountEntity> accountEntities);

	AccountEntity find(Long accountNumber);

	Iterable<AccountEntity> find(List<Long> ids);

	Iterable<AccountEntity> findAll();

	List<AccountEntity> update(Map<Long, String> updatorMap);

	AccountEntity updateAmount(Long accountId, int updateAmount);

	AccountEntity update(AccountEntity accountEntity);

	void delete(Long accountId);

	void delete(List<Long> ids);

	void deleteAll();

}