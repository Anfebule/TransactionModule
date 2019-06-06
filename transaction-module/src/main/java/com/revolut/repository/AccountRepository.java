package com.revolut.repository;

import com.revolut.model.Account;

/**
 * Provides CRUD methods needed within the app for Account entity
 */
public interface AccountRepository {

    /**
     * Create a new account
     * @param account account to be created
     * @return account number
     */
    Integer addAccount (Account account);

    /**
     * Updates an account balance
     * Update account balance
     * @param account account to be updated
     */
    void updateAccountBalance (Account account);

    /**
     * Get an account information by account number
     * @param accountNumber account number to search for
     * @return account found
     */
    Account getAccountById (Integer accountNumber);
}
