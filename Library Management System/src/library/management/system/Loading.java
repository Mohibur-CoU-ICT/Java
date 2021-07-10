package library.management.system;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Loading extends JFrame implements Runnable {

    private final JPanel panel;
    private final JProgressBar progressBar;
    private final int frame_length = 800, frame_height = 600;
    private final int field_length = 120, field_width = 25, no_of_field = 8, y_gap = 50;
    private final int y_start = (frame_height - no_of_field * y_gap) / 2;
    String useridString;
    Thread th;

    Loading(String useridString) {
        this.useridString = useridString;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Loading");
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        th = new Thread((Runnable) this);

        panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel l1 = new JLabel("Library Management System");
        l1.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
        l1.setForeground(new Color(70, 140, 210));
        l1.setBounds((frame_length - 470) / 2, y_start, 4 * field_length, 2 * field_width);
        panel.add(l1);

        progressBar = new JProgressBar();
        progressBar.setBounds((frame_length - 3 * field_length) / 2, y_start + 3 * y_gap, 3 * field_length, field_width);
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        JLabel l2 = new JLabel("Please wait.....");
        l2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        l2.setForeground(new Color(160, 80, 40));
        l2.setBounds((frame_length - field_length) / 2, y_start + 5 * y_gap, field_length, field_width);
        panel.add(l2);

        th.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                int m = progressBar.getMaximum();
                int v = progressBar.getValue();
//                System.out.println(i + " " + m + " " + v);
                progressBar.setValue(progressBar.getValue() + 1);
                Thread.sleep(50);
            }
            dispose();
            new Home(useridString).setVisible(true);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Loading("").setVisible(true);
    }

}
