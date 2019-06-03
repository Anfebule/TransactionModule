package com.revolut.service;

import com.revolut.model.Account;
import com.revolut.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Provides CRUD methods needed within the app for Account entity
 */
public class AccountService {

    private static SessionFactory factory;
    private Session session;

    /**
     * Initialize hibernate session
     */
    public AccountService() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        initializeDatabase(2);
    }

    /**
     * Initializes database with sample data
     */
    private void initializeDatabase(int recordAmount){
        for (int i = 0; i < recordAmount; i++){
            User user = new User("User"+i, "3015555");
            Account account = new Account(i,1000, user);
            addAccount(account);
        }
    }

    /**
     * Create a new account
     * @param account account to be created
     * @return account number
     */
    public Integer addAccount (Account account){
        Transaction tx = null;
        Integer accountId = null;

        try {
            tx = session.beginTransaction();
            accountId = (Integer) session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return accountId;
    }

    /**
     * Update account balance
     * @param account account to be updated
     */
    public void updateAccountBalance (Account account){
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Account accountFound = session.get(Account.class, account.getAccountNumber());
            accountFound.setBalance(account.getBalance());
            session.update(accountFound);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Gets account by id
     * @param accountNumber account number to search for
     * @return account found
     */
    public Account getAccountById (Integer accountNumber) {
        Transaction tx = null;
        Account accountFound;

        try {
            tx = session.beginTransaction();
            accountFound = session.get(Account.class, accountNumber);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return accountFound;
    }

    public void closeSession(){
        session.close();
    }
}
