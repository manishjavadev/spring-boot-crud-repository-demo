package com.manish.javadev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.manish.javadev.model.AccountEntity;
import com.manish.javadev.service.AccountService;

@SpringBootApplication
public class SpringBootApplicationDemo implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationDemo.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// Save data
		saveData();

		// Find data
		findData();

		// Update data
		updateData();

		// Delete data
		deleteData();
	}

	private void saveData() {
		// Save Single Entity
		AccountEntity accountEntity = new AccountEntity("Saving Account", "Manish", new Double(2300));
		accountService.save(accountEntity);

		// Save Multiple Entity
		List<AccountEntity> accountEntities = new ArrayList<>();
		AccountEntity accountEntity1 = new AccountEntity("Saving Account", "Bala JI", new Double(2500));
		AccountEntity accountEntity2 = new AccountEntity("Current Account", "Shashi Mishra", new Double(2000));
		AccountEntity accountEntity3 = new AccountEntity("Credit Account", "Varsha", new Double(1800));

		accountEntities.add(accountEntity1);
		accountEntities.add(accountEntity2);
		accountEntities.add(accountEntity3);
		accountService.saveAll(accountEntities);
	}

	private void findData() {
		System.out.println("ReadFromDatabase called");

		// find By Id
		System.out.println("Find AccountEntity By ID");
		AccountEntity resultAccountEntity = accountService.find(new Long(1));
		System.out.println(resultAccountEntity);

		// find By Ids
		System.out.println("Find Multiple AccountEntity By IDS");
		List<Long> ids = new ArrayList<>();
		ids.add(new Long(1));
		ids.add(new Long(2));
		ids.add(new Long(3));
		Iterable<AccountEntity> accountEntityLists = accountService.find(ids);
		accountEntityLists.forEach(System.out::println);

		// find All
		System.out.println("Find All AccountEntity");
		accountEntityLists = accountService.findAll();
		accountEntityLists.forEach(System.out::println);

	}

	private void updateData() {
		System.out.println("UpdateAccountEntity called");

		// Update AccountEntity By Id
		System.out.println("Find AccountEntity By ID");
		AccountEntity resultAccountEntity = accountService.updateAmount(new Long(1), 10000);
		System.out.println(resultAccountEntity);

		// Update AccountEntity By Ids
		System.out.println("Update Multiple AccountEntity By IDS");
		Map<Long, String> updatorMap = new HashMap<>();
		updatorMap.put(new Long(1), "Salary Account");
		updatorMap.put(new Long(2), "PPF Account");
		List<AccountEntity> accountEntityLists = accountService.update(updatorMap);
		accountEntityLists.forEach(System.out::println);

	}

	private void deleteData() {
		System.out.println("DeleteAccountEntity called");

		// Delete AccountEntity By Id
		System.out.println("Delete AccountEntity By ID");
		accountService.delete(new Long(1));

		// Delete AccountEntity By Ids
		System.out.println("Delete Multiple AccountEntity By IDS");
		List<Long> ids = new ArrayList<>();
		ids.add(new Long(2));
		ids.add(new Long(3));
		accountService.delete(ids);

		// Delete All AccountEntity
		accountService.deleteAll();
	}
}