package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private ArrayList<Account> userAccounts;
    private static int idCount;

    public User(String userName, int userID) {
        this.userName = userName;
        this.userID = userID;
        this.idCount = 0;
        this.userAccounts = new ArrayList<Account>();
        // For phase 1: Users will have a fixed limit of 2 accounts
        addUserAccount(new Account(idCount++, "Chequing"));
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

    public String accountsToString() {
        String output = " ";
        for (int i = 0; i < userAccounts.size(); i++) {
            output += "\n";
            output += "Account: " + userAccounts.get(i).getAccountNum() + ", ";
            output += "Type: " + userAccounts.get(i).getAccountType();
            output += "Balance: $" + userAccounts.get(i).getFunds();
        }
        return output;
    }

    public void addFunds(Account a, double amount) {
        a.addFunds(amount);
    }

    public void withdrawFunds(Account a, double amount) {
        a.withdrawFunds(amount);
    }

    public void addUserAccount(Account acc) {
        userAccounts.add(acc);
    }

}