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
    public void createAccount(@RequestBody Account account) {                            //TODO this should return info about success/failure
        accountCreateService.create(account);
    }

    @RequestMapping("/all")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

}
