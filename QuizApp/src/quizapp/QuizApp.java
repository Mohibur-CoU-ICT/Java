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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class QuizApp extends JFrame implements ActionListener {

    JButton nextButton, exitButton;
    JTextField nameTextField;

    QuizApp() {
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

        JLabel l2 = new JLabel("Quiz Application");
        l2.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        l2.setForeground(Color.BLUE);
        l2.setBounds(130, 10, 400, 50);
        add(l2);

        JLabel l3 = new JLabel("Enter your name");
        l3.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
        l3.setForeground(new Color(30, 144, 254));
        l3.setBounds(230, 100, 400, 50);
        add(l3);

        nameTextField = new JTextField();
        nameTextField.setBounds(150, 150, 300, 30);
        nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(nameTextField);

        nextButton = new JButton("Next");
        nextButton.setBounds(150, 300, 100, 30);
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        nextButton.setFocusable(false);
        add(nextButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(350, 300, 100, 30);
        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            String username = nameTextField.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(this, "Enter your name first", "Quiz Application", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new Rules(username).setVisible(true);
            }
        } else if (ae.getSource() == exitButton) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("About Developer")) {
            new AboutDeveloper().setVisible(true);
        } else if (ae.getActionCommand().equals("About Quiz App")) {
            new AboutQuizApp().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new QuizApp().setVisible(true);
    }

}
