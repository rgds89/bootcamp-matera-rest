package com.matera.bootcamp.bank.service;

import com.matera.bootcamp.bank.exception.InvalidAccountException;
import com.matera.bootcamp.bank.model.Account;
import com.matera.bootcamp.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createOrUpdateAccount(Account account) throws InvalidAccountException {
        if(isNull(account.getBranch())){
            throw  new InvalidAccountException(String.format("A conta não possui agência"));
        }
        return accountRepository.save(account);
    }

    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }

    public Account debitAccount(Long id, BigDecimal value) throws InvalidAccountException {
        Optional<Account> account = findById(id);
        if(account.isPresent()){
            account.get().debit(value);
            return accountRepository.save(account.get());
        }
        throw new InvalidAccountException("Error");
    }

    public Account creditAccount(Long id, BigDecimal value) throws InvalidAccountException {
        Optional<Account> account = findById(id);
        if(account.isPresent()){
            account.get().credit(value);
            return accountRepository.save(account.get());
        }
        throw new InvalidAccountException("Error");
    }

    public void delete(Account account){
        accountRepository.delete(account);
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}
