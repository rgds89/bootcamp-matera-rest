package com.matera.bootcamp.bank.controller;

import com.matera.bootcamp.bank.exception.InvalidAccountException;
import com.matera.bootcamp.bank.model.Account;
import com.matera.bootcamp.bank.model.Holder;
import com.matera.bootcamp.bank.service.AccountService;
import com.matera.bootcamp.bank.service.HolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final HolderService holderService;

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/entry/{idAccount}/debit/{value}")
    public ResponseEntity<Account> debit(@PathVariable Long idAccount, @PathVariable BigDecimal value) throws InvalidAccountException {
        Account account = accountService.debitAccount(idAccount, value);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PostMapping("/entry/{idAccount}/credit/{value}")
    public ResponseEntity<Account> credit(@PathVariable Long idAccount, @PathVariable BigDecimal value) throws InvalidAccountException {
        Account account = accountService.creditAccount(idAccount, value);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PostMapping
    public ResponseEntity<Account> newAccount(@RequestBody Account account) throws InvalidAccountException {
        Holder holder = account.getHolder();
        Holder holderCreated = holderService.createOrUpdateHolder(holder);
        account.setHolder(holderCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createOrUpdateAccount(account));
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<Account> findById(@PathVariable Long idAccount) {
        Optional<Account> accountOptional = accountService.findById(idAccount);
        if (accountOptional.isPresent()) {
            return ResponseEntity.ok(accountOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idAccount}")
    public ResponseEntity<Account> update(@PathVariable Long idAccount, @RequestBody Account accountUpdated) throws InvalidAccountException {
        Optional<Account> accountOptional = accountService.findById(idAccount);
        if (accountOptional.isPresent()) {
            accountUpdated.setId(idAccount);
            return ResponseEntity.ok(accountService.createOrUpdateAccount(accountUpdated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Void> delete(@PathVariable Long idAccount) {
        Optional<Account> accountOptional = accountService.findById(idAccount);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            accountService.delete(account);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
