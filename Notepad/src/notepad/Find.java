package notepad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Find extends JFrame implements ActionListener {

    JTextArea area;
    JTextField findText;
    JButton findButton, cancelButton;

    Find(JTextArea param) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 200, 350, 160);
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Find");
        setLayout(null);
        area = param;

        JLabel l1 = new JLabel("Find what: ");
        l1.setBounds(20, 20, 60, 25);
        add(l1);

        findText = new JTextField();
        findText.setBounds(90, 20, 220, 25);
        add(findText);

        findButton = new JButton("Find");
        findButton.setBounds(50, 70, 100, 25);
        findButton.addActionListener(this);
        add(findButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 70, 100, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Find")) {
            findText();
        } else if (ae.getActionCommand().equals("Cancel")) {
            area.setSelectionColor(area.getDisabledTextColor());
            dispose();
        }
    }

    void findText() {
        String text = area.getText();
        String key = findText.getText();
        int searchIndex = area.getCaretPosition();
        if (key.length() > 0) {
            searchIndex = text.indexOf(key, searchIndex);
            if (searchIndex != -1) {
                area.select(searchIndex, searchIndex + key.length());
                area.setSelectionColor(Color.GREEN);
//                System.out.println(searchIndex + " " + area.getSelectedText() + " " + area.getSelectionColor());
            } else {
                JOptionPane.showMessageDialog(this, key + " not found", "Notepad", JOptionPane.INFORMATION_MESSAGE);
            }
            searchIndex += key.length();
        } else {
            JOptionPane.showMessageDialog(this, "Empty key", "Notepad", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Find(new JTextArea()).setVisible(true);
    }

}
