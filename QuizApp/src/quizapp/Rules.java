package quizapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Rules extends JFrame implements ActionListener {

    JButton backButton, startButton;

    Rules(String username) {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(388, 150, 600, 450);
        setTitle("Quiz Application");
        setResizable(false);
        setLayout(null);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);
        setJMenuBar(menubar);

        JMenu about = new JMenu("About");
        menubar.add(about);

        JMenuItem aboutDeveloper = new JMenuItem("About Developer");
        aboutDeveloper.addActionListener(this);
        about.add(aboutDeveloper);

        JMenuItem aboutQuizApp = new JMenuItem("About Quiz App");
        aboutQuizApp.addActionListener(this);
        about.add(aboutQuizApp);

        JLabel l1 = new JLabel();
        l1.setForeground(new Color(30, 144, 255));
        l1.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
        l1.setBounds(50, 20, 550, 30);
        l1.setText(username + " Welcome to quiz exam.");
        add(l1);

        JLabel l2 = new JLabel();
        l1.setForeground(new Color(30, 144, 255));
        l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        l2.setBounds(10, 55, 580, 200);
        l2.setText("<html>"
                + "1. Read the question carefully.<br>"
                + "2. Close your books and all related materials.<br>"
                + "3. Don't try to copy from others.<br>"
                + "4. There are total of 20 questions.<br>"
                + "5. You have total 10 minutes to answer all questions.<br>"
                + "6. For each correct answer you will get 5 points.<br>"
                + "7. Crying is allowed but don't make sound.<br>"
                + "8. Press Start button to start the quiz.<br>"
                + "</html>");
        add(l2);

        backButton = new JButton("Back");
        backButton.setBounds(30, 300, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        add(backButton);

        startButton = new JButton("Start");
        startButton.setBounds(450, 300, 100, 30);
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        add(startButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("About Developer")) {
            new AboutDeveloper().setVisible(true);
        } else if (ae.getActionCommand().equals("Back")) {
            dispose();
            new QuizApp().setVisible(true);
        } else if (ae.getActionCommand().equals("Start")) {
            dispose();
            new Question().setVisible(true);
        } else if (ae.getActionCommand().equals("About Quiz App")) {
            new AboutQuizApp().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Rules("").setVisible(true);
    }

}
