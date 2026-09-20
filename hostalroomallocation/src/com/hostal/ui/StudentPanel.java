package com.hostal.ui;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.hostal.model.students;
import com.hostal.service.studentService;
import com.hostal.service.studentServiceImp1;

public class StudentPanel extends JPanel {

    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField phoneField;
    private JTextField roomIdField;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private studentService studentService;

    // Colors
    private final Color SIDEBAR = new Color(25, 32, 45);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color BLUE = new Color(45, 110, 180);
    private final Color GREEN = new Color(45, 140, 85);
    private final Color RED = new Color(190, 65, 65);

    public StudentPanel() {

        studentService = new studentServiceImp1();

        

        createUI();
        loadStudents();
    }

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // ==================================================
        // SIDEBAR
        // ==================================================

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(210, 700));
        sidebar.setBackground(SIDEBAR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("HostelHub");
        logo.setForeground(WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 25));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        JButton dashboardBtn = createMenuButton("Dashboard");
        JButton studentBtn = createMenuButton("Students");
        JButton roomBtn = createMenuButton("Rooms");
        JButton allocationBtn = createMenuButton("Allocation");
        JButton recordsBtn = createMenuButton("Allocations");
        JButton vacateBtn = createMenuButton("Vacate Room");

        studentBtn.setBackground(new Color(55, 65, 85));

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

        // ==================================================
        // CONTENT
        // ==================================================

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(BACKGROUND);

        // ---------- HEADER ----------

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(BACKGROUND);
        header.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 15, 30)
        );

        JLabel title = new JLabel("Student Management");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel subtitle = new JLabel(
                "Manage hostel student information"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        content.add(header, BorderLayout.NORTH);

        // ==================================================
        // FORM CARD
        // ==================================================

        JPanel formCard = new JPanel(new BorderLayout());
        formCard.setBackground(WHITE);
        formCard.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 15, 25)
        );

        JLabel formTitle = new JLabel("Student Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 19));

        formCard.add(formTitle, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 5, 15, 8));
        fieldsPanel.setBackground(WHITE);

        studentIdField = new JTextField();
        nameField = new JTextField();
        departmentField = new JTextField();
        phoneField = new JTextField();
        roomIdField = new JTextField();

        fieldsPanel.add(createLabelPanel(
                "Student ID", studentIdField));

        fieldsPanel.add(createLabelPanel(
                "Name", nameField));

        fieldsPanel.add(createLabelPanel(
                "Department", departmentField));

        fieldsPanel.add(createLabelPanel(
                "Phone", phoneField));

        fieldsPanel.add(createLabelPanel(
                "Room ID", roomIdField));

        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(new JLabel(""));

        formCard.add(fieldsPanel, BorderLayout.CENTER);

        // ==================================================
        // BUTTONS
        // ==================================================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 10)
        );

        buttonPanel.setBackground(WHITE);

        JButton addButton = createButton(
                "Add Student", GREEN);

        JButton updateButton = createButton(
                "Update", BLUE);

        JButton deleteButton = createButton(
                "Delete", RED);

        JButton clearButton = createButton(
                "Clear", new Color(100, 100, 100));

        JButton refreshButton = createButton(
                "Refresh", new Color(70, 100, 130));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        formCard.add(buttonPanel, BorderLayout.SOUTH);

        // ==================================================
        // TABLE
        // ==================================================

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(WHITE);
        tableCard.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        );

        JLabel tableTitle = new JLabel("Student Records");
        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        tableCard.add(tableTitle, BorderLayout.NORTH);

        String[] columns = {
                "Student ID",
                "Name",
                "Department",
                "Phone",
                "Room ID"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        studentTable = new JTable(tableModel);

        studentTable.setRowHeight(30);
        studentTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        studentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        studentTable.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(studentTable);

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ==================================================
        // CENTER CONTENT
        // ==================================================

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

        // ==================================================
        // BUTTON EVENTS
        // ==================================================

        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadStudents()
        );

        // ==================================================
        // TABLE ROW CLICK
        // ==================================================

        studentTable.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row =
                        studentTable.getSelectedRow();

                if (row != -1) {

                    studentIdField.setText(
                            tableModel.getValueAt(
                                    row, 0).toString()
                    );

                    nameField.setText(
                            tableModel.getValueAt(
                                    row, 1).toString()
                    );

                    departmentField.setText(
                            tableModel.getValueAt(
                                    row, 2).toString()
                    );

                    phoneField.setText(
                            tableModel.getValueAt(
                                    row, 3).toString()
                    );

                    roomIdField.setText(
                            tableModel.getValueAt(
                                    row, 4).toString()
                    );
                }
            }
        });

        // ==================================================
        // ADD TO FRAME
        // ==================================================

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);
    }

    // ==================================================
    // ADD STUDENT
    // ==================================================

    private void addStudent() {

        try {

            int studentId =
                    Integer.parseInt(
                            studentIdField.getText().trim());

            String name =
                    nameField.getText().trim();

            String department =
                    departmentField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim());

            students student =
                    new students(
                            studentId,
                            name,
                            department,
                            phone,
                            roomId
                    );

            boolean result =
                    studentService.addStudent(student);

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadStudents();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add student.",
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
        }
    }

    // ==================================================
    // UPDATE STUDENT
    // ==================================================

    private void updateStudent() {

        try {

            int studentId =
                    Integer.parseInt(
                            studentIdField.getText().trim());

            String name =
                    nameField.getText().trim();

            String department =
                    departmentField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            int roomId =
                    Integer.parseInt(
                            roomIdField.getText().trim());

            students student =
                    new students(
                            studentId,
                            name,
                            department,
                            phone,
                            roomId
                    );

            boolean result =
                    studentService.updateStudent(student);

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadStudents();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student update failed.",
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

    // ==================================================
    // DELETE STUDENT
    // ==================================================

    private void deleteStudent() {

        try {

            int studentId =
                    Integer.parseInt(
                            studentIdField.getText().trim());

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete Student ID "
                                    + studentId + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                boolean result =
                        studentService
                                .deleteStudent(studentId);

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                    loadStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deletion failed.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Student ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ==================================================
    // LOAD STUDENTS
    // ==================================================

    private void loadStudents() {

        tableModel.setRowCount(0);

        List<students> studentsList =
                studentService.getAllStudents();

        for (students student : studentsList) {

            Object[] row = {

                    student.getStudentId(),
                    student.getName(),
                    student.getDepartment(),
                    student.getPhone(),
                    student.getRoomId()
            };

            tableModel.addRow(row);
        }
    }

    // ==================================================
    // CLEAR
    // ==================================================

    private void clearFields() {

        studentIdField.setText("");
        nameField.setText("");
        departmentField.setText("");
        phoneField.setText("");
        roomIdField.setText("");

        studentTable.clearSelection();
    }

    // ==================================================
    // MENU BUTTON
    // ==================================================

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

    // ==================================================
    // ACTION BUTTON
    // ==================================================

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

    // ==================================================
    // FIELD PANEL
    // ==================================================

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

    // ==================================================
    // MAIN
    // ==================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentPanel panel =
                    new StudentPanel();

            panel.setVisible(true);
        });
    }
}