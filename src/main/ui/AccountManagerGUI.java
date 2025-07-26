package ui;

import model.Account;  // Update with your actual package path

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.Account;
import model.User;


public class AccountManagerGUI extends JFrame implements ActionListener {
    private DefaultListModel<Account> accountListModel;
    private JList<Account> accountJList;
    private JTextField typeField;
    private JTextField balanceField;
    private int accountNumCounter = 1;

    

    public AccountManagerGUI() {
        super("Bank Account Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Type:"));
        typeField = new JTextField(8);
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Balance:"));
        balanceField = new JTextField(5);
        inputPanel.add(balanceField);

        JButton addBtn = new JButton("Add Account");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);
        inputPanel.add(addBtn);

        JButton filterBtn = new JButton("Show Accounts > $0");
        filterBtn.setActionCommand("filter");
        filterBtn.addActionListener(this);
        inputPanel.add(filterBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Account list display
        accountListModel = new DefaultListModel<>();
        accountJList = new JList<>(accountListModel);
        add(new JScrollPane(accountJList), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("add")) {
            String accountType = typeField.getText();
            double funds;
        
            try {
                funds = Double.parseDouble(balanceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for funds.");
                return;
            }
        
            int accountNum = accountNumCounter++;  // increment unique account ID
        
            Account acc = new Account(accountNum, accountType, funds);
            accountListModel.addElement(acc);
        
            // Clear input fields
            typeField.setText("");
            balanceField.setText("");
        }

        if (cmd.equals("filter")) {
            DefaultListModel<Account> filteredModel = new DefaultListModel<>();
            for (int i = 0; i < accountListModel.size(); i++) {
                Account acc = accountListModel.get(i);
                if (acc.getFunds() > 0) {
                    filteredModel.addElement(acc);
                }
            }
            accountJList.setModel(filteredModel);
        }
    }

    public static void main(String[] args) {
        new AccountManagerGUI();
    }
}