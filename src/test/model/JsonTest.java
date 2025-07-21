package model;

import model.Account;
import model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAccount(int expectedNum, String expectedType, double expectedBalance, Account account) {
        assertEquals(expectedNum, account.getAccountNum());
        assertEquals(expectedType, account.getAccountType());
        assertEquals(expectedBalance, account.getFunds());
    }
}