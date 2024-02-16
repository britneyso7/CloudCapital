package model;

public class Account {
    private int accountNum;
    private String accountType;
    private double funds;

    public Account(int accountNum, String accountType) {
        this.accountNum = accountNum;
        this.accountType = accountType;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public void addFunds(double increment) {
        funds = funds + increment;
    }

    public void withdrawFunds(double increment) {
        funds = funds - increment;
    }

    public double getFunds() {
        return funds;
    }




}
