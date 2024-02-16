package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private ArrayList<Account> userAccounts;

    public User(String userName, int userID) {
        this.userName = userName;
        this.userID = userID;
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

    public void addFunds(Account a, double amount) {
        a.addFunds(amount);
    }

    public void withdrawFunds(Account a, double amount) {
        a.withdrawFunds(amount);
    }

    public void addUserAccounts(Account acc) {
        userAccounts.add(acc);
    }

    public void transferFunds(int aaaNum, int bbbNum, double fund) {
        Account takeFromAccount = null;
        Account giveToAccount  = null;

        for (int i = 0; i < userAccounts.size(); i++) {
            if (userAccounts.get(i).getAccountNum() == aaaNum) {
                takeFromAccount = userAccounts.get(i);
            } else if (userAccounts.get(i).getAccountNum() == bbbNum) {
                giveToAccount = userAccounts.get(i);
            }
        }

        takeFromAccount.withdrawFunds(fund);
        giveToAccount.addFunds(fund);
    }
}