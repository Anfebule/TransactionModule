package com.revolut.repository;

import com.revolut.model.Account;

public interface AccountRepository {
    //void initializeDatabase(int recordsAmount);
    Integer addAccount (Account account);
    void updateAccountBalance (Account account);
    Account getAccountById (Integer accountNumber);
}
