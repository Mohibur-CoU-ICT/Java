package library.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LibraryManagementSystem extends JFrame implements ActionListener {

    private final JButton nextButton;
    private final JLabel contentPane;
    private final int frame_length = 800, frame_height = 600;

    public LibraryManagementSystem() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Library Management System");
        setResizable(false);
        setLayout(null);
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/library.jpg"));
        Image i2 = i1.getImage().getScaledInstance(frame_length, frame_height, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        contentPane = new JLabel(i3);
        contentPane.setBounds(0, 0, frame_length, frame_height);
        add(contentPane);

        nextButton = new JButton("Next");
        createButton(nextButton, (int) (.75 * frame_length), (int) (.75 * frame_height), 100, 25);
    }

    private void createButton(JButton btn, int a, int b, int c, int d) {
        btn.setBounds(a, b, c, d);
        btn.setBackground(Color.ORANGE);
        btn.addActionListener(this);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            dispose();
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem().setVisible(true);
    }

}
