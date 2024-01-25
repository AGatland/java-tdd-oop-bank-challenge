package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void testCurrentAccountConstructor() {
        Customer customer = new Customer("1", "Java Man", "12345678", "java@man.coder");
        Account account = new Account("1", customer);
        Assertions.assertEquals("1", account.getAccountId());
        Assertions.assertEquals(customer, account.getCustomer());
        Assertions.assertEquals(0.0, account.getBalance());
        Assertions.assertTrue(account.getTransactions().isEmpty());
    }

    @Test
    public void testTransactionListInstantiated() {
        Customer customer = new Customer("1", "Java Man", "12345678", "java@man.coder");
        Account account = new Account("1", customer);
        account.getTransactions().add(new Transaction(new Date(),100, TransactionType.WITHDRAW ,1000));
        Assertions.assertFalse(account.getTransactions().isEmpty());
    }

    @Test
    public void testGetBankStatementWithTransaction() {
        Customer customer = new Customer("1", "Java Man", "12345678", "java@man.coder");
        Account account = new Account("1", customer);
        account.getTransactions().add(new Transaction(new Date(),100, TransactionType.DEPOSIT, 100));
        Assertions.assertEquals("", account.getBankStatement());
    }

    @Test
    public void testGetBankStatementWithMultipleTransactions() {
        Customer customer = new Customer("1", "Java Man", "12345678", "java@man.coder");
        Account account = new Account("1", customer);
        account.getTransactions().add(new Transaction(new Date(),200, TransactionType.WITHDRAW, 900));
        account.getTransactions().add(new Transaction(new Date(),100, TransactionType.DEPOSIT, 1100));
        account.getTransactions().add(new Transaction(new Date(),1000, TransactionType.DEPOSIT, 1000));
        Assertions.assertEquals("", account.getBankStatement());
    }

    @Test
    public void testGetBankStatementWithoutTransaction() {
        Customer customer = new Customer("1", "Java Man", "12345678", "java@man.coder");
        Account account = new Account("1", customer);
        Assertions.assertEquals("No transactions", account.getBankStatement());
    }
}