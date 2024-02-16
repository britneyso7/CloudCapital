package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private ArrayList<Account> userAccounts;

    public User(String userName) {
        userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Account> getUserAccounts() {
        return userAccounts;
    }

    public void addUserAccounts(Account acc) {
        userAccounts.add(acc);
    }

    public void transferFunds(int aum, int bum, double fund) {

    }

}
