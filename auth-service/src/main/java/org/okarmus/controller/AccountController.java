package org.okarmus.controller;

import org.okarmus.domain.Account;
import org.okarmus.repository.AccountRepository;
import org.okarmus.service.create.AccountCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 22.11.16.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountCreateService accountCreateService;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = POST)
    public ResponseEntity<Long> createAccount(@RequestBody Account account) {
        long id = accountCreateService.create(account);
        return new ResponseEntity<>(id, CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") long id) {
        accountCreateService.delete(id);
        return new ResponseEntity<>(ACCEPTED);
    }


    @RequestMapping("/current")
    public Principal getAccount(Principal principal) { return principal;}

    @RequestMapping("/all")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
