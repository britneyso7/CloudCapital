package model;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Represents a user in banking system with unique ID
 * name, and a list of an arbitrary amount of bank accounts
 */
public class User {
    private int userID;
    private String userName;
    private ArrayList<Account> userAccounts;
    private static int idCount;

    public User(String userName, int userID) {
        this.userName = userName;
        this.userID = userID;
        this.userAccounts = new ArrayList<Account>();
       
        addUserAccount(new Account(idCount++, "Chequing", 0));
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Account> getUserAccounts() {
        return userAccounts;
    }

/**
 * EFFECTS: Returns a string summary of all accounts under
 * this user with the account ID, type, and balance for each 
 * account with each account.
 * 
 */
    public String accountsToString() {
        String output = "";
        for (int i = 0; i < userAccounts.size(); i++) {
            output += "\n";
            output += "Account: " + userAccounts.get(i).getAccountNum() + ", ";
            output += "Type: " + userAccounts.get(i).getAccountType();
            output += "Balance: $" + userAccounts.get(i).getFunds();
        }
        return output;
    }

/**
 * REQUIRES: a != null, amount >= 0
 * MODIFIES: a
 * EFFECTS: Adds funds to the given account
 * @param a
 * @param amount
 */
    public void addFunds(Account a, double amount) {
        a.addFunds(amount);
    }

/**
 * REQUIRES: a != null, amount >= 0
 * MODIFIES: a
 * EFFECTS: Withdraws funds from given account
 */

    public void withdrawFunds(Account a, double amount) {
        a.withdrawFunds(amount);
    }

/**
 * REQUIRES: acc != null
 * MODIFIES: acc
 * EFFECTS: Adds acc to a list of User accounts
 */
    public void addUserAccount(Account acc) {
        userAccounts.add(acc);
    }


// Modelled from WorkRoom in JsonSerializationDemo project

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userName", userName);      // 
        json.put("userID", userID);          // 
        json.put("accounts", accountsToJson());
        return json;
}

// EFFECTS: returns accounts in this user as a JSON array
        private JSONArray accountsToJson() {
            JSONArray jsonArray = new JSONArray();

            for (Account a : userAccounts) {   // Replace with your list field name
                jsonArray.put(a.toJson());
            }

            return jsonArray;
        }
}
