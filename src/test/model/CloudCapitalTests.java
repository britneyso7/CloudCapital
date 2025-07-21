package model;

import model.Account;
import model.User;
import ui.CloudCapital;
import ui.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class CloudCapitalTests {
    private User testUser;
    private Account testAccount;
    private CloudCapital testCloudCapital;

    @BeforeEach
    void testSetup() {
        testUser = new User("bso7", 3);
        testAccount = new Account(1, "chequing", 0);
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
    void testAccountWithdrawl(){
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
        testUser.addUserAccount(new Account(2,"savings", 0));
        assertEquals("savings", testUser.getUserAccounts().get(1).getAccountType());
        testUser.addUserAccount(new Account(3,"chequing", 0));
        assertEquals("chequing", testUser.getUserAccounts().get(2).getAccountType());
    }

    @Test
    void testExit(){

    }

    @Test
    void testCCDesposit(){
        String simulatedInput = "Britney\nx\n0\n500\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        testCloudCapital.processSelection(testUser, 1);
        testCloudCapital.addFunds(testUser);
        assertEquals(500, testUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testCCWithdraw(){
        String simulatedInput = "Britney\nx\n0\n500\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        testCloudCapital = new CloudCapital();
        testCloudCapital.processSelection(testUser, 1);
        testCloudCapital.withdrawFunds(testUser);
        assertEquals(-500, testUser.getUserAccounts().get(0).getFunds());
    }

    @Test
    void testCCPrint(){
        String simulatedInput = "Britney\n4";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        assertEquals(
                "\nAccount: 0, Type: ChequingBalance: $0.0",
                testUser.accountsToString()
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