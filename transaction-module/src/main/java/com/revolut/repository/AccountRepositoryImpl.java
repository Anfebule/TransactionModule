package com.revolut.repository;

import com.revolut.model.Account;
import com.revolut.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.transaction.Transactional;

/**
 * Provides CRUD methods needed within the app for Account entity
 */
public class AccountRepositoryImpl implements AccountRepository {

    private static SessionFactory factory;
    private Session session;

    /**
     * Initialize hibernate session
     */
    public AccountRepositoryImpl() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        initializeDatabase(2);
    }

    /**
     * Initializes database with sample data
     */
    private void initializeDatabase(int recordsAmount){

        for (int i = 0; i < recordsAmount; i++){
            User user = new User("User"+i, "3015555");
            Account account = new Account(i, Double.valueOf("1000"), user);
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
            session = factory.openSession();
            tx = session.beginTransaction();
            accountId = (Integer) session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
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
            session = factory.openSession();
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
        } finally {
            session.close();
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
            session = factory.openSession();
            tx = session.beginTransaction();
            accountFound = session.get(Account.class, accountNumber);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
        return accountFound;
    }
}
