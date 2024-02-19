package ui;

import model.User;

import model.*;

import java.util.Scanner;

/*
    For phase 1 the following user stories are addressed
    1) As a client, I want to be able to add funds to my account
    2) As a client, I want to be able to withdraw funds from my account
    3) As a client, I want to be able to view my account totals and history
    4) As a client, I want to be able to view multiple account totals at once
 */
public class CloudCapital {
    Scanner input;
    private static int idCount;

    public CloudCapital() {
        runCloudCapital();
    }

    public User currentUser;

    private void runCloudCapital() {
        input = new Scanner(System.in);
        idCount = 0;
        boolean keepGoing = true;

        printGreeting();
        currentUser = createUser();

        while (keepGoing) {
            char operation = 'x';
            printMenu();
            operation = input.nextLine().charAt(0);
            System.out.println("You have selected " + operation);
            if (operation == 'x') {
                break;
            }
            processSelection(currentUser, operation);
        }
        System.out.println("You are now exiting CloudCapital, Goodbye");
    }

    private User createUser() {
        System.out.println("What is your name?");
        String name = input.nextLine();
        return (new User(name, idCount++));
    }

    private void processSelection(User currentUser, int operation) {
        if (operation == '1') {
            addFunds(currentUser);
        } else if (operation == '2') {
            withdrawFunds(currentUser);
        } else if (operation == '3') {
            createNewAccount(currentUser);
        } else if (operation == '4') {
            System.out.println(currentUser.accountsToString());
        } else {
            System.out.println("You did not make a valid selection please choose an operation or exit");
        }
    }

    private void addFunds(User currentUser) {
        System.out.println("Enter the account number you want to add to");
        System.out.println(currentUser.accountsToString());
        Account a = currentUser.getUserAccounts().get(input.nextInt());
        input.nextLine();
        System.out.println("How much are you depositing?");
        a.addFunds(input.nextDouble());
        input.nextLine();
        System.out.println("Deposit complete!");
    }

    private void withdrawFunds(User currentUser) {
        System.out.println("Enter the account number you want to withdraw from");
        System.out.println(currentUser.accountsToString());
        Account a = currentUser.getUserAccounts().get(input.nextInt());
        input.nextLine();
        System.out.println("How much are you withdrawing?");
        a.withdrawFunds(input.nextDouble());
        input.nextLine();
        System.out.println("Deposit complete!");
    }

    private void createNewAccount(User u) {
        int accountType = 0;
        while (accountType < 1 || accountType > 2) {
            System.out.println("What kind of account do you want to create? \n"
                    + "(Enter the number of the account type)");
            System.out.println("1. Chequing\n2. Savings");
            accountType = input.nextInt();
            input.nextLine();
            if (accountType == 1) {
                u.addUserAccount(new Account(idCount, "chequing"));
            } else if (accountType == 2) {
                u.addUserAccount(new Account(idCount, "savings"));
            } else {
                System.out.println("That was no a valid selection, please try again");
            }
        }
        System.out.println("Account created!");
    }

    private void printGreeting() {
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to Cloud Capital");
        System.out.println("\tStart by creating an account");
        System.out.println("--------------------------------------------");
    }

    private void printMenu() {
        System.out.println("Please select from the following (input the number/letter of the desired operation");
        System.out.println("1. Add Funds");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. Create new account");
        System.out.println("4. View Account Balances");
        System.out.println("x. Exit");
    }
}
