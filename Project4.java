/* File: Project4.java
 * Author: Kevin Abrahams
 * Date: 31-03-2020
 * Purpose: Create a gui enabling users to insert, delete, and find property information in a database.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Project4 extends JFrame {
    // treemap serving as database (storing all properties)
    TreeMap<Integer, Property> database = new TreeMap<Integer, Property>();
    
    // three possible database actions (stored in a JComboBox)
    String[] databaseActions = {"Insert", "Delete", "Find"};
    
    // three possible statuses to select for a property (stored in a JComboBox)
    String[] statuses = {"FOR_SALE", "UNDER_CONTRACT", "SOLD"};
    
    // column 1 components
    private JLabel transactionLabel = new JLabel("Transaction No: ");
    private JLabel addressLabel = new JLabel("Address: ");
    private JLabel bedroomsLabel = new JLabel("Bedrooms: ");
    private JLabel footageLabel = new JLabel("Square Footage");
    private JLabel priceLabel = new JLabel("Price: ");
    private JButton btnProcess = new JButton("Process");
    private JButton btnStatus = new JButton("Change Status");
    
    // column 2 components
    private JTextField transactionField = new JTextField();
    private JTextField addressField = new JTextField();
    private JTextField bedroomsField = new JTextField();
    private JTextField footageField = new JTextField();
    private JTextField priceField = new JTextField();
    private JComboBox databaseOptions = new JComboBox(databaseActions);
    private JComboBox statusOptions = new JComboBox(statuses);
    public Project4() {
        setLayout(null);
        
        
        // form two columns with absolute positioning
        // for each column, the x should be the same value (same x for every gui componenet in each column)
        // sizing and positioning the first column
        transactionLabel.setBounds(3, 10, 120, 20);
        addressLabel.setBounds(3, 40, 120, 20);
        bedroomsLabel.setBounds(3, 70, 120, 20);
        footageLabel.setBounds(3, 100, 120, 20);
        priceLabel.setBounds(3, 130, 120, 20);
        btnProcess.setBounds(0, 160, 150, 20);
        btnStatus.setBounds(0, 190, 150, 20);
        
        // sizing and positioning the second column (even spacing and same x coordinate)
        transactionField.setBounds(160, 10, 150, 25);
        addressField.setBounds(160, 40, 150, 25);
        bedroomsField.setBounds(160, 70, 150, 25);
        footageField.setBounds(160, 100, 150, 25);
        priceField.setBounds(160, 130, 150, 25);
        databaseOptions.setBounds(160, 160, 150, 25);
        statusOptions.setBounds(160, 190, 150, 25);
        
        // adding first column components to frame's content pane
        add(transactionLabel);
        add(addressLabel);
        add(bedroomsLabel);
        add(footageLabel);
        add(priceLabel);
        add(btnProcess);
        // adding action listener to process button
        btnProcess.addActionListener(new ActionListener() {
            // used to store property object created with info entered by user
            Property property;
            // each of these booleans represents whether valid values have been entered in each textfield
            // requiring only integers/digits
            boolean goodTransaction = true;
            boolean goodAddress = true;
            boolean goodBedrooms = true;
            boolean goodFootage = true;
            boolean goodPrice = true;
            
            
            int transaction, bedrooms, footage, price;
            String address = "";
            
            public void actionPerformed(ActionEvent e) {
                // at the beginning of action listener, reset property values to 0
                transaction = bedrooms = footage = price = 0;
                // make sure only digits/numbers entered in transaction field
                for (int i = 0; i < transactionField.getText().length(); i++) {
                    if (!Character.isDigit(transactionField.getText().charAt(i))) {
                        // signify invalid entry
                        goodTransaction = false;
                        // display error message 
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: In the transaction field, only enter digits/integers, no words, letters, or any other kinds of symbols.");
                        break;
                    }
                }
                
                // make sure only digits/numbers entered in bedrooms field
                for (int i = 0; i < bedroomsField.getText().length(); i++) {
                    if (!Character.isDigit(bedroomsField.getText().charAt(i))) {
                        // signify invalid entry
                        goodBedrooms = false;
                        // display error message
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: In the bedrooms field, only enter digits/integers, no words, letters, or any other kinds of symbols.");
                        break;
                    }
                }
                
                // make sure only digits/numbers entered in footage field
                for (int i = 0; i < footageField.getText().length(); i++) {
                    if (!Character.isDigit(footageField.getText().charAt(i))) {
                        // signify invalid entry
                        goodFootage = false;
                        // display error message
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: In the footage field, only enter digits/integers, no words, letters, or any other kinds of symbols.");
                        break;
                    }
                }
                
                // make sure only digits/numbers entered in price field
                for (int i = 0; i < priceField.getText().length(); i++) {
                    if (!Character.isDigit(priceField.getText().charAt(i))) {
                        // signify invalid entry
                        goodPrice = false;
                        // display error message
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: In the price field, only enter digits/integers, no words, letters, or any other kinds of symbols.");
                        break;
                    }
                }
                
                // store each value (transaction, address, bedrooms, and price)
                // if they are valid entries in the respective textfields
                if (goodTransaction)
                    transaction = Integer.parseInt(transactionField.getText());
                if (goodAddress)
                    address = addressField.getText();
                if (goodBedrooms)
                    bedrooms = Integer.parseInt(bedroomsField.getText());
                if (goodFootage)
                    footage = Integer.parseInt(footageField.getText());
                if (goodPrice)
                    price = Integer.parseInt(priceField.getText());
                
                // if each textfield contains valid entry, proceed with creating Property object
                if (goodTransaction && goodAddress && goodBedrooms && goodFootage && goodPrice)
                    property = new Property(address, bedrooms, footage, price);
                
                // perform  if selected database action was insert
                if (databaseOptions.getSelectedItem().equals("Insert")) {
                    // display error if key already exists
                    if (database.containsKey(transaction)) {
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: Duplicate transaction number exists, so new property could not be added to database.");
                    }
                    
                    else {
                        // insert new property to database if key doesnt already exist
                        database.put(transaction, property);
                        // display successful operation message
                        JOptionPane.showMessageDialog(getContentPane(), "Successfully added new property to database");
                    }
                }
                
                // perform if selected database action was delete
                if (databaseOptions.getSelectedItem().equals("Delete")) {
                    // attempt remove property with specified info from database
                    // display error message if exception occurs
                    try {
                        
                        if (database.containsKey(transaction)) {
                            database.remove(transaction);
                            JOptionPane.showMessageDialog(getContentPane(), "Successfully deleted property from database.");
                        }
                        else
                            JOptionPane.showMessageDialog(getContentPane(), "ERROR: No property with the specified transaction number exists within database.");
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: No property with the specified transaction number exists within database.");
                    }
                }
                
                // perform if selected database action was find
                if (databaseOptions.getSelectedItem().equals("Find")) {
                    // attempt to find a property in the database and display its info
                    // display error message if exception occurs
                    try {
                        Property foundProperty = database.get(transaction);
                        JOptionPane.showMessageDialog(getContentPane(), "Successfully found the property with the provided transaction number. Below is the property's info:\n" + foundProperty.toString());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: The property with the provided information could not be found.");
                    }
                }
            }
        });
        // end of action listener for btn process
        
        add(btnStatus);
        // adding action listener to status button
        btnStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Property property;
                int transaction = 0;
                boolean goodTransaction = true;
                
                // first verify valid entry in transaction field
                for (int i = 0; i < transactionField.getText().length(); i++) {
                    if (!Character.isDigit(transactionField.getText().charAt(i))) {
                        goodTransaction = false;
                        JOptionPane.showMessageDialog(getContentPane(), "ERROR: In the transaction field, only enter digits/integers, no words, letters, or any other kinds of symbols.");
                        break;
                    }
                }
                
                // if transaction entry is valid, store it in variable
                if (goodTransaction)
                    transaction = Integer.parseInt(transactionField.getText());
                try {
                    // attempt to retrieve property with matching key from database
                    property = database.get(transaction);
                    // change the status of the property to the one selected by user
                    if (((String)statusOptions.getSelectedItem()).equals(Status.UNDER_CONTRACT.name()))
                        property.changeState(Status.UNDER_CONTRACT);
                    if (((String)statusOptions.getSelectedItem()).equals(Status.FOR_SALE.name()))
                        property.changeState(Status.FOR_SALE);
                    if (((String)statusOptions.getSelectedItem()).equals(Status.SOLD.name()))
                        property.changeState(Status.SOLD);
                     JOptionPane.showMessageDialog(getContentPane(), "Successfully changed the status of the database property with the provided transaction number.");
                } catch (Exception exception) {
                    // display error message if unable to change status
                    JOptionPane.showMessageDialog(getContentPane(), "ERROR OCCURRED");
                }
                
            }
        });
        // adding second column components to frame's content pane
        add(transactionField);
        add(addressField);
        add(bedroomsField);
        add(footageField);
        add(priceField);
        add(databaseOptions);
        add(statusOptions);
        
        setTitle("Real Estate Database");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
    }
    
    public static void main(String[] args) {
        Project4 frame = new Project4();
    }
    
}
