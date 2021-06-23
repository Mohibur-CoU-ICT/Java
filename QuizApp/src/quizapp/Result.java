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

public class Result extends JFrame implements ActionListener {

    JButton againButton, exitButton;

    Result(String resultLog, int correctAnswer) {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(388, 150, 600, 450);
        setTitle("Quiz Exam Result");
        setResizable(false);
        setLayout(null);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu about = new JMenu("About");
        menubar.setBackground(Color.WHITE);
        menubar.add(about);

        JMenuItem aboutDeveloper = new JMenuItem("About Developer");
        aboutDeveloper.addActionListener(this);
        about.add(aboutDeveloper);

        JMenuItem aboutQuizApp = new JMenuItem("About Quiz App");
        aboutQuizApp.addActionListener(this);
        about.add(aboutQuizApp);

        JLabel scoreJLabel = new JLabel();
        scoreJLabel.setBounds(240, 0, 120, 20);
        scoreJLabel.setText("Score = " + Integer.toString(correctAnswer * 5));
        scoreJLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        scoreJLabel.setForeground(Color.GREEN);
        add(scoreJLabel);

        JLabel answerLogJLabel = new JLabel();
        answerLogJLabel.setBounds(150, 20, 400, 330);
        answerLogJLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        answerLogJLabel.setBackground(Color.GREEN);
        answerLogJLabel.setText(resultLog.toString());
        add(answerLogJLabel);

        againButton = new JButton("Again");
        againButton.setBounds(150, 350, 100, 30);
        againButton.setBackground(Color.BLUE);
        againButton.setForeground(Color.WHITE);
        againButton.addActionListener(this);
        againButton.setFocusable(false);
        add(againButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(350, 350, 100, 30);
        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("About Developer")) {
            new AboutDeveloper().setVisible(true);
        } else if (ae.getActionCommand().equals("Again")) {
            dispose();
            new QuizApp().setVisible(true);
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("About Quiz App")) {
            new AboutQuizApp().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Result("", 0).setVisible(true);
    }
}
