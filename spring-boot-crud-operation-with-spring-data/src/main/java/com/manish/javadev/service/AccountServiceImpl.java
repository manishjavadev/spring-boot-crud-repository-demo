package com.manish.javadev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.javadev.dao.AccountRepository;
import com.manish.javadev.model.AccountEntity;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public AccountEntity save(AccountEntity accountEntity) {
		AccountEntity acccountResult = accountRepository.save(accountEntity);
		return acccountResult;
	}

	@Override
	public Iterable<AccountEntity> saveAll(List<AccountEntity> accountEntities) {
		return accountRepository.saveAll(accountEntities);
	}

	@Override
	public AccountEntity find(Long accountNumber) {
		// TODO Auto-generated method stub
		Optional<AccountEntity> accounts = accountRepository.findById(accountNumber);
		if (accounts.isPresent()) {
			return accounts.get();
		} else {
			return null;
		}
	}

	@Override
	public Iterable<AccountEntity> find(List<Long> ids) {
		Iterable<AccountEntity> accountEntityList = accountRepository.findAllById(ids);
		return accountEntityList;

	}

	@Override
	public Iterable<AccountEntity> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<AccountEntity> update(Map<Long, String> updatorMap) {
		List<AccountEntity> accountEntities = new ArrayList<>();

		// Java 8 forEach loop, Iterating map and for Each key getting AccountEntity,
		// and updating Account type.
		updatorMap.forEach((key, value) -> {
			// Optional is java 8 final class, get method of that class return object of
			// class.
			Optional<AccountEntity> optional = accountRepository.findById(key);
			AccountEntity accountEntity = optional.get();
			accountEntity.setAccountType(value);
			accountEntities.add(accountEntity);
		});
		Iterable<AccountEntity> saveAccountEntities = accountRepository.saveAll(accountEntities);
		return (List<AccountEntity>) saveAccountEntities;
	}

	@Override
	public AccountEntity updateAmount(Long accountId, int updateAmount) {
		Optional<AccountEntity> accounts = accountRepository.findById(accountId);
		AccountEntity resultEntity = null;
		if (accounts.isPresent()) {
			AccountEntity accountEntity = accounts.get();
			accountEntity.setAmount(accountEntity.getAmount() + updateAmount);
			resultEntity = accountRepository.save(accountEntity);
		}
		return resultEntity;
	}

	@Override
	public AccountEntity update(AccountEntity accountEntity) {
		return accountRepository.save(accountEntity);
	}

	@Override
	public void delete(Long accountNumber) {
		Optional<AccountEntity> accounts = accountRepository.findById(accountNumber);
		if (accounts.isPresent()) {
			accountRepository.delete(accounts.get());
		}
	}

	@Override
	public void delete(List<Long> ids) {
		List<AccountEntity> accountEntities = new ArrayList<>();
		ids.forEach((accountId) -> {
			Optional<AccountEntity> optional = accountRepository.findById(accountId);
			AccountEntity accountEntity = optional.get();
			accountEntities.add(accountEntity);
		});
		accountRepository.deleteAll(accountEntities);

	}

	@Override
	public void deleteAll() {
		accountRepository.deleteAll();
	}
}
