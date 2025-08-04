package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CloudCapitalGUI extends JFrame {
    private static final String JSON_STORE = "./data/user.json";

    private User currentUser;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultComboBoxModel<Account> accountComboBoxModel;
    private JComboBox<Account> accountComboBox;
    private JTextArea accountInfoArea;

    public CloudCapitalGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("CloudCapital");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        currentUser = createUserDialog();

        accountComboBoxModel = new DefaultComboBoxModel<>();
        accountComboBox = new JComboBox<>(accountComboBoxModel);
        accountInfoArea = new JTextArea(10, 30);
        accountInfoArea.setEditable(false);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Accounts", createAccountPanel());
        tabs.add("Manage Funds", createFundPanel());
        tabs.add("Save/Load", createSaveLoadPanel());

        add(tabs);
        setVisible(true);
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton addAccountBtn = new JButton("Add New Account");
        addAccountBtn.addActionListener(e -> createAccountDialog());

        panel.add(accountComboBox, BorderLayout.NORTH);
        panel.add(new JScrollPane(accountInfoArea), BorderLayout.CENTER);
        panel.add(addAccountBtn, BorderLayout.SOUTH);

        refreshAccountView();
        return panel;
    }

    private JPanel createFundPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JButton addBtn = new JButton("Add Funds");
        JButton withdrawBtn = new JButton("Withdraw Funds");

        addBtn.addActionListener(e -> handleFundOperation(true));
        withdrawBtn.addActionListener(e -> handleFundOperation(false));

        panel.add(new JLabel("Selected Account:"));
        panel.add(accountComboBox);
        panel.add(addBtn);
        panel.add(withdrawBtn);
        return panel;
    }

    private JPanel createSaveLoadPanel() {
        JPanel panel = new JPanel();

        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        saveBtn.addActionListener(e -> saveUser());
        loadBtn.addActionListener(e -> {
            loadUser();
            refreshAccountView();
        });

        panel.add(saveBtn);
        panel.add(loadBtn);
        return panel;
    }

    private User createUserDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        if (name == null || name.trim().isEmpty()) {
            name = "DefaultUser";
        }
        return new User(name, 0);
    }

    private void createAccountDialog() {
        String[] options = {"Chequing", "Savings"};
        int choice = JOptionPane.showOptionDialog(this,
                "Select account type:",
                "Create Account",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0 || choice == 1) {
            String type = options[choice].toLowerCase();
            Account newAccount = new Account(currentUser.getUserAccounts().size(), type, 0);
            currentUser.addUserAccount(newAccount);
            refreshAccountView();
            JOptionPane.showMessageDialog(this, type + " account created.");
        }
    }

    private void handleFundOperation(boolean isDeposit) {
        Account selected = (Account) accountComboBox.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "No account selected.");
            return;
        }

        String prompt = isDeposit ? "Enter amount to deposit:" : "Enter amount to withdraw:";
        String input = JOptionPane.showInputDialog(this, prompt);

        try {
            double amount = Double.parseDouble(input);
            if (isDeposit) {
                selected.addFunds(amount);
            } else {
                selected.withdrawFunds(amount);
            }
            refreshAccountView();
            JOptionPane.showMessageDialog(this, "Transaction successful.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    private void refreshAccountView() {
        accountComboBoxModel.removeAllElements();
        StringBuilder sb = new StringBuilder();
        for (Account acc : currentUser.getUserAccounts()) {
            accountComboBoxModel.addElement(acc);
            sb.append("[").append(acc.getAccountType()).append(" - $")
              .append(acc.getFunds()).append("]\n");
        }
        accountInfoArea.setText(sb.toString());
    }

    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentUser);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "User saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving user.");
        }
    }

    private void loadUser() {
        try {
            currentUser = jsonReader.read();
            JOptionPane.showMessageDialog(this, "User loaded: " + currentUser.getUserName());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user.");
        }
    }

    public static void main(String[] args) {
        new CloudCapitalGUI();
    }
}