package model;

import ui.CloudCapital;
import ui.Main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class CloudCapitalTests {
    private User testUser;
    private Account testAccount;
    private CloudCapital testCloudCapital;

    @BeforeEach
    void testSetup() {
        testUser = new User("bso7", 3);
        testAccount = new Account(1, "chequing");
    }

    @Test
    void testAccountID(){
        assertEquals(1, testAccount.getAccountNum());
    }

    @Test
    void testAccountDeposit(){
        testUser.addFunds(testUser.getUserAccounts().get(0), 500);
        assertEquals(500, testUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testAccountWithdrawal(){
        testUser.withdrawFunds(testUser.getUserAccounts().get(0), 250);
        assertEquals(-250, testUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testAccountToString(){
        assertEquals("\nAccount: 0, Type: ChequingBalance: $0.0", testUser.accountsToString());
    }

    //USER LEVEL TESTS
    @Test
    void testUserName(){
        assertEquals("bso7", testUser.getUserName());
    }

    @Test
    void testUserID(){
        assertEquals(3, testUser.getUserID());
    }

    @Test
    void testCreateAccount(){
        testUser.addUserAccount(new Account(2,"savings"));
        assertEquals("savings", testUser.getUserAccounts().get(1).getAccountType());
        testUser.addUserAccount(new Account(3,"chequing"));
        assertEquals("chequing", testUser.getUserAccounts().get(2).getAccountType());
    }

    @Test
    void testExit(){

    }

    @Test
    void testCloudCapitalDeposit() {
        String simulatedInput = "Britney\n1\n0\n500\nx\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        assertEquals(500, testCloudCapital.currentUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testCloudCapitalWithdrawal() {
        String simulatedInput = "Britney\n2\n0\n500\nx\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        assertEquals(-500, testCloudCapital.currentUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testCloudCapitalNewChequingAccount() {
        String simulatedInput = "Britney\n3\n1\n4\nx";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        assertEquals(
                "\nAccount: 0, Type: ChequingBalance: $0.0\n" +
                        "Account: 1, Type: chequingBalance: $0.0",
                testCloudCapital.currentUser.accountsToString()
        );
    }

    @Test
    void testCloudCapitalNewSavingsAccount() {
        String simulatedInput = "Britney\n3\n3\n2\n4\nx";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        assertEquals(
                "\nAccount: 0, Type: ChequingBalance: $0.0\n" +
                        "Account: 1, Type: savingsBalance: $0.0",
                testCloudCapital.currentUser.accountsToString()
        );
    }

    //MAIN LEVEL TEST
    @Test
    void testMain(){
        String simulatedInput = "Britney\n5\nx";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        Main.main(new String[0]);
    }
}
