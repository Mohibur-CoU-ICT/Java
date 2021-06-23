package notepad;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutNotepad extends JFrame implements ActionListener {

    JButton b1;

    AboutNotepad() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 100, 600, 400);
        setTitle("About Notepad");
        setAlwaysOnTop(true);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(100, 40, 400, 80);
        add(l1);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(50, 150, 70, 70);
        add(l2);

        JLabel l3 = new JLabel("<html>Notepad is a word processing program,<br>"
                + "which allows changing of text in a computer file.<br>"
                + "Notepad is a simple text editor for basic text-editing program<br>"
                + "which enables computer users to create documents.</html>");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l3.setBounds(150, 80, 500, 230);
        add(l3);

        b1 = new JButton("Close");
        b1.setBounds(250, 320, 80, 25);
        b1.addActionListener(this);
        add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Close")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AboutNotepad().setVisible(true);
    }

}
