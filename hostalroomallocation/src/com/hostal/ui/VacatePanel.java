package com.hostal.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.hostal.model.allocation;
import com.hostal.service.allocationService;
import com.hostal.service.allocationServiceImp1;

public class VacatePanel extends JPanel {

    private allocationService allocationService;
    private JTextField allocationIdField;
    private DefaultTableModel tableModel;
    private JTable table;

    public VacatePanel() {

        allocationService = new allocationServiceImp1();

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(30, 35, 45));
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel title = new JLabel("Vacate Room");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
                "Remove an existing room allocation"
        );
        subtitle.setForeground(new Color(200, 205, 215));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        add(headerPanel, BorderLayout.NORTH);

        // ================= CENTER =================

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        // ================= FORM =================

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Vacate Allocation")
        );

        JLabel allocationLabel =
                new JLabel("Allocation ID:");

        allocationLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        allocationIdField = new JTextField(12);
        allocationIdField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        JButton vacateButton =
                new JButton("Vacate Room");

        vacateButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        vacateButton.setForeground(Color.WHITE);
        vacateButton.setBackground(
                new Color(180, 50, 50)
        );

        vacateButton.setFocusPainted(false);

        JButton clearButton =
                new JButton("Clear");

        clearButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        clearButton.setFocusPainted(false);

        formPanel.add(allocationLabel);
        formPanel.add(allocationIdField);
        formPanel.add(vacateButton);
        formPanel.add(clearButton);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "Allocation ID",
                "Student ID",
                "Room ID",
                "Allocation Date",
                "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        table = new JTable(tableModel);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        table.getTableHeader().setBackground(
                new Color(30, 35, 45)
        );

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Current Allocations"
                )
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(centerPanel, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================

        vacateButton.addActionListener(e ->
                vacateRoom()
        );

        clearButton.addActionListener(e ->
                allocationIdField.setText("")
        );

        // Load existing allocations
        loadAllocations();
    }

    // ================= LOAD ALLOCATIONS =================

    private void loadAllocations() {

        tableModel.setRowCount(0);

        try {

            List<allocation> allocations =
                    allocationService.getAllAllocations();

            for (allocation a : allocations) {

                tableModel.addRow(new Object[]{

                        a.getAllocationId(),
                        a.getStudentId(),
                        a.getRoomId(),
                        a.getAllocationDate(),
                        a.getStatus()
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load allocations.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= VACATE ROOM =================

    private void vacateRoom() {

        String idText =
                allocationIdField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Allocation ID.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int allocationId =
                    Integer.parseInt(idText);

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to vacate this room?",
                            "Confirm Vacate",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }

            boolean result =
                    allocationService.deleteAllocation(
                            allocationId
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room vacated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                allocationIdField.setText("");

                loadAllocations();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room vacation failed.\n"
                                + "Allocation ID not found.",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Allocation ID must be a number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while vacating room.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
