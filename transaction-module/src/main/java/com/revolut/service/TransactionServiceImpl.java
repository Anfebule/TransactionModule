package com.revolut.service;

import com.revolut.model.Account;
import com.revolut.model.TransactionResult;
import com.revolut.repository.AccountRepository;

import javax.inject.Inject;

public class TransactionServiceImpl implements TransactionService {

    private AccountRepository accountRepository;

    @Inject
    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public TransactionResult transfer(Integer recipientAccountNumber, Integer senderAccountNumber, Double amount){

        TransactionResult transactionResult = new TransactionResult();
        Account recipientAccount;
        Account senderAccount;

        try {
            recipientAccount = accountRepository.getAccountById(recipientAccountNumber);
            senderAccount = accountRepository.getAccountById(senderAccountNumber);

            if (recipientAccount != null && senderAccount != null){
                Double senderBalance = senderAccount.getBalance();

                if(senderBalance >= amount){
                    senderAccount.setBalance(senderBalance - amount);
                    recipientAccount.setBalance(recipientAccount.getBalance() + amount);

                    accountRepository.updateAccountBalance(senderAccount);
                    accountRepository.updateAccountBalance(recipientAccount);

                    transactionResult.setMessage("transaction successful");
                    transactionResult.setDescription("transaction successful");
                } else {
                    transactionResult.setMessage("transaction failed");
                    transactionResult.setDescription("insufficient funds");
                }
            } else {
                transactionResult.setMessage("transaction failed");
                transactionResult.setDescription("Account not found");
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return transactionResult;
    }
}
