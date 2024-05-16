package uts_calista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DataApp extends JFrame {
    private final JTextField inputField;
    private final DefaultListModel<String> listModel;
    private final JList<String> dataList;
    private final ArrayList<String> dataStore;

    public DataApp() {
        setTitle("Data Management App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dataStore = new ArrayList<>();

        // Input field
        inputField = new JTextField(20);

        // Buttons
        JButton addButton = new JButton("Add");
        JButton showButton = new JButton("Show Data");
        JButton updateButton = new JButton("Update Data");
        JButton deleteButton = new JButton("Delete Data");

        // List
        listModel = new DefaultListModel<>();
        dataList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(dataList);

        // Panel for input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(showButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Add button action
        addButton.addActionListener((ActionEvent e) -> {
            addData();
        });

        // Show button action
        showButton.addActionListener((ActionEvent e) -> {
            showData();
        });

        // Update button action
        updateButton.addActionListener((ActionEvent e) -> {
            updateData();
        });

        // Delete button action
        deleteButton.addActionListener((ActionEvent e) -> {
            deleteData();
        });
    }

    private void addData() {
        String data = inputField.getText();
        if (!data.isEmpty()) {
            dataStore.add(data);
            listModel.addElement(data);
            inputField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter some data.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showData() {
        listModel.clear();
        for (String data : dataStore) {
            listModel.addElement(data);
        }
    }

    private void updateData() {
        int selectedIndex = dataList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newData = inputField.getText();
            if (!newData.isEmpty()) {
                dataStore.set(selectedIndex, newData);
                showData();
                inputField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter some data.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to update.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteData() {
        int selectedIndex = dataList.getSelectedIndex();
        if (selectedIndex != -1) {
            dataStore.remove(selectedIndex);
            showData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DataApp().setVisible(true);
        });
    }
}
