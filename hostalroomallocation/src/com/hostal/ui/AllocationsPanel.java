package com.hostal.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.hostal.model.allocation;
import com.hostal.service.allocationService;
import com.hostal.service.allocationServiceImp1;

public class AllocationsPanel extends JPanel {

    private allocationService allocationService;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField;

    public AllocationsPanel() {

        allocationService = new allocationServiceImp1();

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ================= HEADER =================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(30, 35, 45));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Allocation Records");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel subtitle = new JLabel("View all hostel room allocation records");
        subtitle.setForeground(new Color(190, 195, 205));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        // ================= SEARCH =================
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        searchPanel.setOpaque(false);

        searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setToolTipText("Search by Allocation ID, Student ID or Room ID");

        JButton searchButton = new JButton("Search");
        styleButton(searchButton);

        JButton refreshButton = new JButton("Refresh");
        styleButton(refreshButton);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        headerPanel.add(searchPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

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
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 35, 45)
        );
        table.getTableHeader().setForeground(Color.WHITE);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(new Color(220, 220, 220));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        add(scrollPane, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================

        searchButton.addActionListener(e -> searchAllocations());

        refreshButton.addActionListener(e -> {
            searchField.setText("");
            loadAllocations();
        });

        searchField.addActionListener(e -> searchAllocations());

        // Load data when panel opens
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
                    "Unable to load allocation records.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= SEARCH =================

    private void searchAllocations() {

        String searchText =
                searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            loadAllocations();
            return;
        }

        tableModel.setRowCount(0);

        try {

            List<allocation> allocations =
                    allocationService.getAllAllocations();

            for (allocation a : allocations) {

                String allocationId =
                        String.valueOf(a.getAllocationId());

                String studentId =
                        String.valueOf(a.getStudentId());

                String roomId =
                        String.valueOf(a.getRoomId());

                String status =
                        a.getStatus() == null
                                ? ""
                                : a.getStatus().toLowerCase();

                if (allocationId.contains(searchText)
                        || studentId.contains(searchText)
                        || roomId.contains(searchText)
                        || status.contains(searchText)) {

                    tableModel.addRow(new Object[]{
                            a.getAllocationId(),
                            a.getStudentId(),
                            a.getRoomId(),
                            a.getAllocationDate(),
                            a.getStatus()
                    });
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Search failed.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= BUTTON STYLE =================

    private void styleButton(JButton button) {

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 80, 95));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(90, 35));
    }
        
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Allocation Records");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 650);
                frame.setLocationRelativeTo(null);

                frame.add(new AllocationsPanel());

            });
    }
}
