package library.management.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class HowToUse extends JFrame implements ActionListener {

    private final JPanel contenPanel;
    private final JButton closeButton;

    public HowToUse() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("How to use");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        contenPanel = new JPanel();
        contenPanel.setLayout(null);
        contenPanel.setBackground(Color.WHITE);
        setContentPane(contenPanel);

        String instructionString = "<html>"
                + "<h2>With Internet Connection:</h2>"
                + "No need to do anything. Just go through the application.<br>But if you see message like \"Couldn't connect to the server\" "
                + "that means my database subscription has ended.<br>You couldn't use the application with internet connection until I renew my subscription.<br>"
                + "You have to go through the without internet connection process."
                + "<h2>Without Internet Connection:</h2>"
                + "<ol>"
                + "<li>Install xampp in C drive.</li>"
                + "<li>Open xampp control panel and start MySQL.</li>"
                + "<li>Open cmd type \"cd \\\"  and press Enter then \"cd xampp\\mysql\\bin\" and press Enter then \"mysql -u root -p\" and press Enter.</li>"
                + "<li>Create a database named library_management_system</li>"
                + "To create the database type create database library_management_system; and press Enter."
                + "<li>Create following 5 tables:</li>"
                + "To create table type the following sql commands."
                + "<ol type='i'>"
                + "<li>create table account(username varchar(30) not null primary key, name varchar(30) not null, password varchar(30) not null, date_of_birth varchar(20) not null, sec_qus varchar(30) not null, sec_ans varchar(30) not null); and press Enter.</li>"
                + "<li>create table book(book_id int(14) not null primary key, book_name varchar(30) not null, writer varchar(30) not null, publisher varchar(30) not null, isbn varchar(30) not null, edition int(5) not null, page int(12) not null); and press Enter.</li>"
                + "<li>create table issue_book(book_id int(14) not null, student_id int(32) not null, date_of_issue varchar(20) not null, primary key(book_id, student_id)); and press Enter.</li>"
                + "<li>create table return_book(book_id int(14) not null, student_id int(32) not null, date_of_return varchar(20) not null, primary key(book_id, student_id)); and press Enter.</li>"
                + "<li>create table student(student_id int(14) not null primary key, student_name varchar(30) not null, father_name varchar(30) not null, faculty varchar(30) not null, department varchar(30) not null, session varchar(10) not null, year varchar(5) not null, semester varchar(5) not null); and press Enter.</li>"
                + "</ol>"
                + "</ol>"
                + "</html>";

        JLabel instruction = new JLabel();
        instruction.setBackground(Color.LIGHT_GRAY);
        instruction.setText(instructionString);
        instruction.setBorder(new EmptyBorder(0, 10, 0, 10));
        instruction.setFont(new Font("Vardana", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 0, 530, 330);
        scrollPane.setViewportView(instruction);
        contenPanel.add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.setBounds(250, 340, 100, 25);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(this);
        contenPanel.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Close")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new HowToUse().setVisible(true);
    }

}
