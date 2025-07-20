
package model;
/** 
 * Bank Account with account number, account type and current funds
 */
public class Account {
    private int accountNum;
    private String accountType;
    private double funds;

    public Account(int accountNum, String accountType, double funds) {
        this.accountNum = accountNum;
        this.accountType = accountType;
        this.funds = 0;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    /**
 * REQUIRES: increment >= 0
 * MODIFIES: this
 * EFFECTS: increases the funds in this account by the given increment
 */
    public void addFunds(double increment) {
        funds = funds + increment;
    }

    /**
 * REQUIRES: increment >= 0
 * MODIFIES: this
 * EFFECTS: subtract increment from account funds if sufficient funds exist;
 *        
 */
    public void withdrawFunds(double increment) {
        funds = funds - increment;
    }

    public double getFunds() {
        return funds;
    }

}