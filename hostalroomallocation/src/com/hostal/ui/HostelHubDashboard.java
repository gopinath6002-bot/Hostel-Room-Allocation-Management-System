package com.hostal.ui;

import javax.swing.*;
import java.awt.*;

public class HostelHubDashboard extends JFrame {

    private CardLayout cardLayout;
    private JPanel content;

    public HostelHubDashboard() {

        setTitle("HostelHub - Room Allocation Management");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 650));
        sidebar.setBackground(new Color(25, 32, 45));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("  HostelHub");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        logo.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 35, 10
                )
        );

        sidebar.add(logo);

        // ================= MENU BUTTONS =================

        JButton dashboardButton =
                createMenuButton("Dashboard");

        JButton studentsButton =
                createMenuButton("Students");

        JButton roomsButton =
                createMenuButton("Rooms");

        JButton allocationButton =
                createMenuButton("Allocation");

        JButton allocationsButton =
                createMenuButton("Allocations");

        JButton vacateButton =
                createMenuButton("Vacate Room");

        sidebar.add(dashboardButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(studentsButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(roomsButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(allocationButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(allocationsButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(vacateButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(Box.createVerticalGlue());

        // ================= EXIT =================

        JButton exitButton = new JButton("Exit");

        exitButton.setMaximumSize(
                new Dimension(200, 45)
        );

        exitButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        exitButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(
                new Color(180, 50, 50)
        );

        exitButton.setFocusPainted(false);

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        sidebar.add(exitButton);
        sidebar.add(Box.createVerticalStrut(25));

        // ================= CONTENT =================

        cardLayout = new CardLayout();

        content = new JPanel(cardLayout);

        content.setBackground(
                new Color(245, 247, 250)
        );

        // ================= DASHBOARD =================

        JPanel dashboardPanel =
                createDashboardPanel();

        content.add(
                dashboardPanel,
                "Dashboard"
        );

        // ================= OTHER PANELS =================

        content.add(
                new StudentPanel(),
                "Students"
        );

        content.add(
                new RoomPanel(),
                "Rooms"
        );

        content.add(
                new AllocationPanel(),
                "Allocation"
        );

        content.add(
                new AllocationsPanel(),
                "Allocations"
        );

        content.add(
                new VacatePanel(),
                "Vacate Room"
        );

        // ================= MENU ACTIONS =================

        dashboardButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Dashboard"
                )
        );

        studentsButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Students"
                )
        );

        roomsButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Rooms"
                )
        );

        allocationButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Allocation"
                )
        );

        allocationsButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Allocations"
                )
        );

        vacateButton.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Vacate Room"
                )
        );

        // ================= ADD TO FRAME =================

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                content,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // ==================================================
    // DASHBOARD PANEL
    // ==================================================

    private JPanel createDashboardPanel() {

        JPanel dashboard =
                new JPanel(new BorderLayout());

        dashboard.setBackground(
                new Color(245, 247, 250)
        );

        // ================= HEADER =================

        JLabel heading =
                new JLabel("Dashboard");

        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        JLabel subtitle =
                new JLabel(
                        "Hostel Room Allocation Management System"
                );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(
                new Color(245, 247, 250)
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 20, 30
                )
        );

        header.add(heading);

        header.add(
                Box.createVerticalStrut(5)
        );

        header.add(subtitle);

        dashboard.add(
                header,
                BorderLayout.NORTH
        );

        // ================= STATISTICS =================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1, 4, 15, 15
                        )
                );

        statsPanel.setBackground(
                new Color(245, 247, 250)
        );

        statsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 30, 20, 30
                )
        );

        statsPanel.add(
                createCard(
                        "Total Students",
                        "5"
                )
        );

        statsPanel.add(
                createCard(
                        "Total Rooms",
                        "5"
                )
        );

        statsPanel.add(
                createCard(
                        "Allocated Rooms",
                        "1"
                )
        );

        statsPanel.add(
                createCard(
                        "Available Rooms",
                        "4"
                )
        );

        // ================= QUICK ACTIONS =================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                new Color(245, 247, 250)
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 30, 30, 30
                )
        );

        JLabel quickTitle =
                new JLabel("Quick Operations");

        quickTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JPanel actionPanel =
                new JPanel(
                        new GridLayout(
                                2, 2, 15, 15
                        )
                );

        actionPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton addStudent =
                createActionButton(
                        "Add Student"
                );

        JButton addRoom =
                createActionButton(
                        "Add Room"
                );

        JButton allocate =
                createActionButton(
                        "Allocate Room"
                );

        JButton view =
                createActionButton(
                        "View Allocations"
                );

        // Quick operation navigation

        addStudent.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Students"
                )
        );

        addRoom.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Rooms"
                )
        );

        allocate.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Allocation"
                )
        );

        view.addActionListener(
                e -> cardLayout.show(
                        content,
                        "Allocations"
                )
        );

        actionPanel.add(addStudent);
        actionPanel.add(addRoom);
        actionPanel.add(allocate);
        actionPanel.add(view);

        bottomPanel.add(
                quickTitle,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                actionPanel,
                BorderLayout.CENTER
        );

        // ================= CENTER =================

        JPanel center =
                new JPanel(
                        new BorderLayout()
                );

        center.setBackground(
                new Color(245, 247, 250)
        );

        center.add(
                statsPanel,
                BorderLayout.NORTH
        );

        center.add(
                bottomPanel,
                BorderLayout.CENTER
        );

        dashboard.add(
                center,
                BorderLayout.CENTER
        );

        return dashboard;
    }

    // ==================================================
    // MENU BUTTON
    // ==================================================

    private JButton createMenuButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(200, 45)
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(25, 32, 45)
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }

    // ==================================================
    // STAT CARD
    // ==================================================

    private JPanel createCard(
            String title,
            String value) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(valueLabel);

        return card;
    }

    // ==================================================
    // QUICK ACTION BUTTON
    // ==================================================

    private JButton createActionButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        return button;
    }

    // ==================================================
    // MAIN
    // ==================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HostelHubDashboard dashboard =
                    new HostelHubDashboard();

            dashboard.setVisible(true);
        });
    }
}