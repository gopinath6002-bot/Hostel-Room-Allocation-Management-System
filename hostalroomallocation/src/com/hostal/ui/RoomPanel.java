package com.hostal.ui;

import javax.swing.*;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.hostal.model.room;
import com.hostal.service.RoomService;
import com.hostal.service.RoomServiceImp1;

public class RoomPanel extends JPanel {

    private JTextField roomIdField;
    private JTextField roomTypeField;
    private JTextField capacityField;
    private JTextField occupiedField;
    private JTextField statusField;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    private RoomService roomService;

    private final Color SIDEBAR = new Color(25, 32, 45);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color BLUE = new Color(45, 110, 180);
    private final Color GREEN = new Color(45, 140, 85);
    private final Color RED = new Color(190, 65, 65);

    public RoomPanel() {

        roomService = new RoomServiceImp1();

        

        createUI();
        loadRooms();
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

        roomBtn.setBackground(new Color(55, 65, 85));

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

        JLabel title = new JLabel("Room Management");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel subtitle = new JLabel(
                "Manage hostel rooms, capacity and occupancy"
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

        JLabel formTitle = new JLabel("Room Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 19));

        formCard.add(formTitle, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(
                new GridLayout(2, 5, 15, 8)
        );
        fieldsPanel.setBackground(WHITE);

        roomIdField = new JTextField();
        roomTypeField = new JTextField();
        capacityField = new JTextField();
        occupiedField = new JTextField();
        statusField = new JTextField();

        fieldsPanel.add(
                createLabelPanel("Room ID", roomIdField)
        );

        fieldsPanel.add(
                createLabelPanel("Room Type", roomTypeField)
        );

        fieldsPanel.add(
                createLabelPanel("Capacity", capacityField)
        );

        fieldsPanel.add(
                createLabelPanel("Occupied", occupiedField)
        );

        fieldsPanel.add(
                createLabelPanel("Status", statusField)
        );

        fieldsPanel.add(new JLabel(""));
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

        JButton addButton =
                createButton("Add Room", GREEN);

        JButton updateButton =
                createButton("Update", BLUE);

        JButton deleteButton =
                createButton("Delete", RED);

        JButton clearButton =
                createButton("Clear", new Color(100, 100, 100));

        JButton refreshButton =
                createButton("Refresh", new Color(70, 100, 130));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        formCard.add(buttonPanel, BorderLayout.SOUTH);

        // ================= TABLE =================

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(WHITE);
        tableCard.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        );

        JLabel tableTitle = new JLabel("Room Records");
        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        tableCard.add(tableTitle, BorderLayout.NORTH);

        String[] columns = {
                "Room ID",
                "Room Type",
                "Capacity",
                "Occupied",
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

        roomTable = new JTable(tableModel);

        roomTable.setRowHeight(30);
        roomTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        roomTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        roomTable.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(roomTable);

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

        addButton.addActionListener(e -> addRoom());

        updateButton.addActionListener(e -> updateRoom());

        deleteButton.addActionListener(e -> deleteRoom());

        clearButton.addActionListener(e -> clearFields());

        refreshButton.addActionListener(e -> loadRooms());

        // ================= TABLE CLICK =================

        roomTable.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = roomTable.getSelectedRow();

                if (row != -1) {

                    roomIdField.setText(
                            tableModel.getValueAt(
                                    row, 0).toString()
                    );

                    roomTypeField.setText(
                            tableModel.getValueAt(
                                    row, 1).toString()
                    );

                    capacityField.setText(
                            tableModel.getValueAt(
                                    row, 2).toString()
                    );

                    occupiedField.setText(
                            tableModel.getValueAt(
                                    row, 3).toString()
                    );

                    statusField.setText(
                            tableModel.getValueAt(
                                    row, 4).toString()
                    );
                }
            }
        });

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);
    }

    // ================= ADD ROOM =================

    private void addRoom() {

        try {

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim());

            String roomType =
                    roomTypeField.getText().trim();

            int capacity =
                    Integer.parseInt(
                            capacityField.getText().trim());

            int occupied =
                    Integer.parseInt(
                            occupiedField.getText().trim());

            String status =
                    statusField.getText().trim();

            room newRoom = new room(
                    roomId,
                    roomType,
                    capacity,
                    occupied,
                    status
            );

            boolean result =
                    roomService.addRoom(newRoom);

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadRooms();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add room.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room ID, Capacity and Occupied must be numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ================= UPDATE ROOM =================

    private void updateRoom() {

        try {

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim());

            String roomType =
                    roomTypeField.getText().trim();

            int capacity =
                    Integer.parseInt(
                            capacityField.getText().trim());

            int occupied =
                    Integer.parseInt(
                            occupiedField.getText().trim());

            String status =
                    statusField.getText().trim();

            room updatedRoom = new room(
                    roomId,
                    roomType,
                    capacity,
                    occupied,
                    status
            );

            boolean result =
                    roomService.updateRoom(updatedRoom);

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadRooms();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room update failed.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ================= DELETE ROOM =================

    private void deleteRoom() {

        try {

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim());

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete Room ID "
                                    + roomId + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                boolean result =
                        roomService.deleteRoom(roomId);

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                    loadRooms();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room deletion failed.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Room ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ================= LOAD ROOMS =================

    private void loadRooms() {

        tableModel.setRowCount(0);

        List<room> roomList =
                roomService.getAllRooms();

        for (room r : roomList) {

            Object[] row = {

                    r.getroomId(),
                    r.getRoomType(),
                    r.getCapacity(),
                    r.getOccupied(),
                    r.getStatus()
            };

            tableModel.addRow(row);
        }
    }

    // ================= CLEAR =================

    private void clearFields() {

        roomIdField.setText("");
        roomTypeField.setText("");
        capacityField.setText("");
        occupiedField.setText("");
        statusField.setText("");

        roomTable.clearSelection();
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


    }

