package quizapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutQuizApp extends JFrame implements ActionListener {

    JButton closeButton;
    
    AboutQuizApp() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 100, 450, 410);
        setTitle("About QuizApp");
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quizapp/icons/quiz.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(20, 0, 400, 200);
        add(l1);

        JLabel l3 = new JLabel("<html>"
                + "This app helps Java learners to increase their<br>"
                + "knowledge of Java, test their Java knowledge.<br>"
                + "It could be helpful to prepare your Java exams.<br>"
                + "It will provide you an environment like exam hall.<br>"
                + "</html>");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l3.setBounds(50, 200, 350, 100);
        l3.setBackground(Color.yellow);
        add(l3);

        closeButton = new JButton("Close");
        closeButton.setBounds(180, 330, 70, 25);
        closeButton.setBackground(Color.WHITE);
        closeButton.addActionListener(this);
        closeButton.setFocusable(false);
        add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Close")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AboutQuizApp().setVisible(true);
    }

}
