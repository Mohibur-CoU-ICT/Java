package notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Goto extends JFrame implements ActionListener {

    JTextArea area;
    JTextField lineNo;
    JButton gotoButton, cancelButton;

    Goto(JTextArea param) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 200, 350, 160);
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Goto");
        setLayout(null);
        area = param;

        JLabel l1 = new JLabel("Goto Line No: ");
        l1.setBounds(20, 20, 100, 25);
        add(l1);

        lineNo = new JTextField();
        lineNo.setBounds(100, 20, 80, 25);
        add(lineNo);

        gotoButton = new JButton("Goto");
        gotoButton.setBounds(50, 70, 100, 25);
        gotoButton.addActionListener(this);
        add(gotoButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 70, 100, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Goto")) {
            try {
                TreeMap<Integer, Integer> lineMap = new TreeMap<>();
                lineMap.put(1, 0);
                int line = Integer.parseInt(lineNo.getText());
                int availableLine = 1;
                String text = area.getText();
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == '\n') {
                        availableLine++;
                        lineMap.put(availableLine, i + 1);
                    }
                }
                if (line <= availableLine) {
                    area.setCaretPosition(lineMap.get(line));
                } else {
                    JOptionPane.showMessageDialog(this, "The document has only " + availableLine + " lines", "Notepad", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Input line number", "Notepad", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Input at least 1", "Notepad", JOptionPane.WARNING_MESSAGE);
            }
        } else if (ae.getActionCommand().equals("Cancel")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new Goto(new JTextArea()).setVisible(true);
    }

}
