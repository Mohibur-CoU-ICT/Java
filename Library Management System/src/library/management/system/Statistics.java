package library.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;

public class Statistics extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTable issueBookTable, returnBookTable;
    private final JButton backButton;
    String useridString;

    public Statistics(String useridString) {
        this.useridString = useridString;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Statistics");
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/back.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        backButton = new JButton("Back");
        backButton.setBounds(15, 0, 100, 25);
        backButton.setBackground(Color.ORANGE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setIcon(i3);
        contentPane.add(backButton);

        issueBookTable = new JTable();
        issueBookTable.setDefaultEditor(Object.class, null);    // to disable user editing of cell values

        JScrollPane issueBookScrollPane = new JScrollPane();
        issueBookScrollPane.setBounds(15, 50, 760, 225);
        issueBookScrollPane.setViewportView(issueBookTable);
        contentPane.add(issueBookScrollPane);

        returnBookTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To disable user editing of cell values
            }
        };

        JScrollPane returnBookScrollPane = new JScrollPane();
        returnBookScrollPane.setBounds(15, 310, 760, 225);
        returnBookScrollPane.setViewportView(returnBookTable);
        contentPane.add(returnBookScrollPane);

        JPanel bookPanel = new JPanel();
        createPanel(bookPanel, 10, 30, 770, 250, "Issue Book Details");

        JPanel studentPanel = new JPanel();
        createPanel(studentPanel, 10, 290, 770, 250, "Return Book Details");

        setIssueBookTable();
        setReturnBookTable();
    }

    private void createPanel(JPanel panel, int a, int b, int c, int d, String title) {
        panel.setBounds(a, b, c, d);
        panel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 2), title, TitledBorder.CENTER, TitledBorder.TOP));
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
    }

    private void setIssueBookTable() {
        try {
            Conn con = new Conn();
            String sql = "select * from issue_book;";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            issueBookTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server\nNo issue book found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setReturnBookTable() {
        try {
            Conn con = new Conn();
            String sql = "select * from return_book;";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            returnBookTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server\nNo return book found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            dispose();
            new Home(useridString).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Statistics("").setVisible(true);
    }

}
