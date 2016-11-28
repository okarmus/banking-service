package org.okarmus.controller;

import org.okarmus.domain.Account;
import org.okarmus.repository.AccountRepository;
import org.okarmus.service.create.AccountCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 22.11.16.
 */
@RestController
@RequestMapping("/account")
public class AcountController {

    @Autowired
    private AccountCreateService accountCreateService;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/current")
    public Principal getAccount(Principal principal) { return principal;}

    @RequestMapping(method = POST)
    public String createAccount(@RequestBody Account account) {                            //TODO this should return info about success/failure
        accountCreateService.create(account);
        return "Auth data has been added sucessfuly";
    }

    @RequestMapping("/all")                             //TODO only admin should have a right to do that
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @RequestMapping
    public String hello() {
        return "hello from auth service";
    }

}
