
package model;

import org.json.JSONObject;
import org.json.JSONArray;

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
        this.funds = funds;
        
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
        EventLog.getInstance().logEvent(new Event("Added transaction: $" + funds));
    }

    /**
     * REQUIRES: increment >= 0
     * MODIFIES: this
     * EFFECTS: subtract increment from account funds if sufficient funds exist;
     * 
     */
    public void withdrawFunds(double increment) {
        funds = funds - increment;
        EventLog.getInstance().logEvent(new Event("Withdrew transaction $" + funds));
    }

    public double getFunds() {
        return funds;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accountNum", accountNum);
        json.put("accountType", accountType);
        json.put("funds", funds);
        return json;
    }

    @Override
    public String toString() {
        return "Account #" + accountNum + " - " + accountType + " ($" + funds + ")";
    }
}