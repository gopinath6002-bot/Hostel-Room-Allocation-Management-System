package com.hostal.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

import com.hostal.model.allocation;
import com.hostal.service.allocationService;
import com.hostal.service.allocationServiceImp1;

public class AllocationPanel extends JPanel {

    private JTextField studentIdField;
    private JTextField roomIdField;
    private JTextField dateField;
    private JTextField statusField;

    private JTable allocationTable;
    private DefaultTableModel tableModel;

    private allocationService allocationService;

    private final Color SIDEBAR = new Color(25, 32, 45);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color BLUE = new Color(45, 110, 180);
    private final Color GREEN = new Color(45, 140, 85);
    private final Color RED = new Color(190, 65, 65);

    public AllocationPanel() {

        allocationService = new allocationServiceImp1();

    

        createUI();
        loadAllocations();
    }

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(210, 700));
        sidebar.setBackground(SIDEBAR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.add(Box.createVerticalStrut(30));

        JLabel logo = new JLabel("HostelHub");
        logo.setForeground(WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 25));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        JButton dashboardBtn = createMenuButton("Dashboard");
        JButton studentBtn = createMenuButton("Students");
        JButton roomBtn = createMenuButton("Rooms");
        JButton allocationBtn = createMenuButton("Allocation");
        JButton recordsBtn = createMenuButton("Allocations");
        JButton vacateBtn = createMenuButton("Vacate Room");

        allocationBtn.setBackground(new Color(55, 65, 85));

        sidebar.add(dashboardBtn);
        sidebar.add(studentBtn);
        sidebar.add(roomBtn);
        sidebar.add(allocationBtn);
        sidebar.add(recordsBtn);
        sidebar.add(vacateBtn);

        sidebar.add(Box.createVerticalGlue());

        JButton exitBtn = createMenuButton("Exit");
        exitBtn.setBackground(RED);

        exitBtn.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

        sidebar.add(exitBtn);
        sidebar.add(Box.createVerticalStrut(25));

        // ================= CONTENT =================

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(BACKGROUND);

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(BACKGROUND);
        header.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 15, 30)
        );

        JLabel title = new JLabel("Room Allocation");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel subtitle = new JLabel(
                "Allocate hostel rooms to registered students"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        content.add(header, BorderLayout.NORTH);

        // ================= FORM CARD =================

        JPanel formCard = new JPanel(new BorderLayout());
        formCard.setBackground(WHITE);
        formCard.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 15, 25)
        );

        JLabel formTitle = new JLabel("Allocation Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 19));

        formCard.add(formTitle, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(
                new GridLayout(2, 4, 15, 8)
        );

        fieldsPanel.setBackground(WHITE);

        studentIdField = new JTextField();
        roomIdField = new JTextField();
        dateField = new JTextField("YYYY-MM-DD");
        statusField = new JTextField("Allocated");

        fieldsPanel.add(
                createLabelPanel("Student ID", studentIdField)
        );

        fieldsPanel.add(
                createLabelPanel("Room ID", roomIdField)
        );

        fieldsPanel.add(
                createLabelPanel("Allocation Date", dateField)
        );

        fieldsPanel.add(
                createLabelPanel("Status", statusField)
        );

        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));

        formCard.add(fieldsPanel, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 10)
        );

        buttonPanel.setBackground(WHITE);

        JButton allocateButton =
                createButton("Allocate Room", GREEN);

        JButton clearButton =
                createButton("Clear", new Color(100, 100, 100));

        JButton refreshButton =
                createButton("Refresh", new Color(70, 100, 130));

        buttonPanel.add(allocateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        formCard.add(buttonPanel, BorderLayout.SOUTH);

        // ================= TABLE =================

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(WHITE);
        tableCard.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        );

        JLabel tableTitle = new JLabel("Allocation Records");
        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        tableCard.add(tableTitle, BorderLayout.NORTH);

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

        allocationTable = new JTable(tableModel);

        allocationTable.setRowHeight(30);
        allocationTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        allocationTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        allocationTable.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        JScrollPane scrollPane =
                new JScrollPane(allocationTable);

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ================= CENTER =================

        JPanel centerPanel = new JPanel(
                new BorderLayout(0, 15)
        );

        centerPanel.setBackground(BACKGROUND);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 30, 25, 30)
        );

        centerPanel.add(
                formCard,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        content.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ================= BUTTON ACTIONS =================

        allocateButton.addActionListener(
                e -> allocateRoom()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadAllocations()
        );

        // ================= ADD TO FRAME =================

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);
    }

    // ================= ALLOCATE ROOM =================

    private void allocateRoom() {

        try {

            int studentId =
                    Integer.parseInt(
                            studentIdField.getText().trim()
                    );

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim()
                    );

            String dateInput =
                    dateField.getText().trim();

            Date allocationDate =
                    Date.valueOf(dateInput);

            String status =
                    statusField.getText().trim();

            allocation newAllocation =
                    new allocation(
                            0,
                            studentId,
                            roomId,
                            allocationDate,
                            status
                    );

            boolean result =
                    allocationService
                            .allocateRoom(newAllocation);

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room allocated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadAllocations();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room allocation failed.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID and Room ID must be numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Date must be in YYYY-MM-DD format.",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ================= LOAD ALLOCATIONS =================

    private void loadAllocations() {

        tableModel.setRowCount(0);

        List<allocation> allocationList =
                allocationService.getAllAllocations();

        for (allocation a : allocationList) {

            Object[] row = {

                    a.getAllocationId(),
                    a.getStudentId(),
                    a.getRoomId(),
                    a.getAllocationDate(),
                    a.getStatus()
            };

            tableModel.addRow(row);
        }
    }

    // ================= CLEAR =================

    private void clearFields() {

        studentIdField.setText("");
        roomIdField.setText("");
        dateField.setText("YYYY-MM-DD");
        statusField.setText("Allocated");

        allocationTable.clearSelection();
    }

    // ================= MENU BUTTON =================

    private JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(
                new Dimension(190, 45)
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(WHITE);
        button.setBackground(SIDEBAR);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }

    // ================= ACTION BUTTON =================

    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        button.setForeground(WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);

        return button;
    }

    // ================= FIELD PANEL =================

    private JPanel createLabelPanel(
            String label,
            JTextField field) {

        JPanel panel = new JPanel(
                new BorderLayout(0, 5)
        );

        panel.setBackground(WHITE);

        JLabel labelText =
                new JLabel(label);

        labelText.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        panel.add(
                labelText,
                BorderLayout.NORTH
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AllocationPanel panel =
                    new AllocationPanel();

            panel.setVisible(true);
        });
    }
}
