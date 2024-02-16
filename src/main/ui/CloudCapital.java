package ui;

import model.User;

import java.util.Scanner;

/*
    For phase 1 the following user stories are addressed
    1) As a client, I want to be able to add funds to my account
    2) As a client, I want to be able to withdraw funds from my account
    3) As a client, I want to be able to open multiple accounts of any kind
    4) As a client, I want to be able to transfer funds between accounts
    5) As a client, I want to be able to view my account totals and history
    6) As a client, I want to be able to view multiple account totals at once
 */
public class CloudCapital {
    Scanner input;
    static int idCount;

    public CloudCapital() {
        runCloudCapital();
    }

    private void runCloudCapital() {
        input = new Scanner(System.in);
        idCount = 0;
        User currentUser;
        boolean keepGoing = true;

        printGreeting();
        currentUser = createUser();

        while (keepGoing) {
            char operation = 'x';
            printMenu();
            operation = input.nextLine().charAt(0);
            System.out.println("You have selected " + operation);
            if (operation == '1') {
                System.out.println("What account do you want to add funds to?");
            } else if (operation == '2') {
                System.out.println("What account do you want to withdraw funds from?");
            } else if (operation == '3') {
            } else if (operation == '4') {
            } else if (operation == 'x') {
                break;
            } else {
                System.out.println("You did not make a valid selection please choose an operation or exit");
            }
        }
    }

    private void printGreeting() {
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to Cloud Capital");
        System.out.println("\tStart by creating an account");
        System.out.println("--------------------------------------------");
    }

    private User createUser() {
        System.out.println("What is your name?");
        String name = input.nextLine();
        return (new User(name, idCount++));
    }

    private void printMenu() {
        System.out.println("Please select from the following options (input number or letter of the desired operation");
        System.out.println("1. Add Funds");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. Transfer Funds");
        System.out.println("4. View Account Balances");
        System.out.println("x. Exit");
    }
}