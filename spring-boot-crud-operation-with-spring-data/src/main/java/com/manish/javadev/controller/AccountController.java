package com.manish.javadev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manish.javadev.model.AccountEntity;
import com.manish.javadev.service.AccountService;

/**
 * @author m.d.srivastava
 * 
 *         http://localhost:8081/api/ping
 * 
 *         http://localhost:8081/api/account/2
 * 
 *         http://localhost:8081/api/account
 *
 */
@RestController
@RequestMapping(value = "/api")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "Configuration is working...";
	}

	@GetMapping(value = "/account/{accountNumber}")
	public ResponseEntity<AccountEntity> find(@PathVariable("accountNumber") Long accountNumber) {
		AccountEntity accountEntity = accountService.find(new Long(accountNumber));
		System.out.println("accountEntity  =:" + accountEntity);
		return new ResponseEntity<AccountEntity>(accountEntity, HttpStatus.OK);
	}

	@PostMapping(value = "/account")
	public ResponseEntity<AccountEntity> save(@RequestBody AccountEntity accountEntity) {
		AccountEntity entity = accountService.save(accountEntity);
		return new ResponseEntity<AccountEntity>(entity, HttpStatus.OK);
	}

	@PutMapping(value = "/account")
	public ResponseEntity<AccountEntity> update(@RequestBody AccountEntity accountEntity) {
		AccountEntity entity = accountService.update(accountEntity);
		return new ResponseEntity<AccountEntity>(entity, HttpStatus.OK);
	}

	@DeleteMapping(value = "/account/{accountNumber}")
	public ResponseEntity<AccountEntity> delete(@PathVariable("accountNumber") Long accountNumber) {
		accountService.delete(new Long(accountNumber));
		return new ResponseEntity<AccountEntity>(HttpStatus.OK);
	}

}