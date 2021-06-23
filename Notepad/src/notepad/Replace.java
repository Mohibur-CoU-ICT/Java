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

public class Replace extends JFrame implements ActionListener {

    JTextArea area;
    JTextField findText, replaceText;

    Replace(JTextArea param) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 200, 350, 160);
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Replace");
        setLayout(null);
        area = param;

        JLabel l1 = new JLabel("Find what : ");
        l1.setBounds(10, 10, 100, 20);
        add(l1);

        findText = new JTextField();
        findText.setBounds(100, 10, 120, 20);
        add(findText);

        JLabel l2 = new JLabel("Replace with : ");
        l2.setBounds(10, 40, 100, 20);
        add(l2);

        replaceText = new JTextField();
        replaceText.setBounds(100, 40, 120, 20);
        add(replaceText);

        JButton findNext = new JButton("Find Next");
        findNext.setBounds(230, 10, 100, 20);
        findNext.addActionListener(this);
        add(findNext);

        JButton replace = new JButton("Replace");
        replace.setBounds(230, 40, 100, 20);
        replace.addActionListener(this);
        add(replace);

        JButton replaceAll = new JButton("Replace All");
        replaceAll.setBounds(230, 70, 100, 20);
        replaceAll.addActionListener(this);
        add(replaceAll);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(230, 100, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Find Next")) {
            if (findText.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Please fill up the field", "Notepad", JOptionPane.ERROR_MESSAGE);
            } else {
                findText();
            }
        } else if (ae.getActionCommand().equals("Replace")) {
            if (findText.getText().length() == 0 || replaceText.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Please fill up the field", "Notepad", JOptionPane.ERROR_MESSAGE);
            } else {
                replaceText();
            }
        } else if (ae.getActionCommand().equals("Replace All")) {
            if (findText.getText().length() == 0 || replaceText.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Please fill up the field", "Notepad", JOptionPane.ERROR_MESSAGE);
            } else {
                replaceAllText();
            }
        } else if (ae.getActionCommand().equals("Cancel")) {
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
            searchIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "Empty key", "Notepad", JOptionPane.ERROR_MESSAGE);
        }
    }

    void replaceText() {
        if (area.getSelectedText() == null) {
            findText();
        } else {
            area.replaceRange(replaceText.getText(), area.getSelectionStart(), area.getSelectionEnd());
        }
    }

    void replaceAllText() {
        String text = area.getText();
        String key = findText.getText();
        String newValue = replaceText.getText();
        String ans = text.replaceAll(key, newValue);
        area.setText(ans);
    }

    public static void main(String[] args) {
        new Replace(new JTextArea()).setVisible(true);
    }

}
