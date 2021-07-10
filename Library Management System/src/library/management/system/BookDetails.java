package library.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

public class BookDetails extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTable table;
    private final JTextField search;
    private final JButton backButton, searchButton, deleteButton;
    String useridString;

    public BookDetails(String useridString) {
        this.useridString = useridString;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Book Details");
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        backButton = new JButton("Back");
        createButton(backButton, 20, 20, 100, 25);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/back.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        backButton.setIcon(i3);

        search = new JTextField();
        search.setBounds(150, 20, 300, 25);
        search.setToolTipText("Search by any field");
        contentPane.add(search);

        searchButton = new JButton("Search");
        createButton(searchButton, 480, 20, 100, 25);
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/search.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        searchButton.setIcon(i6);

        deleteButton = new JButton("Delete");
        createButton(deleteButton, 610, 20, 100, 25);
        deleteButton.setToolTipText("Enter book id in the search box and press Delete to delete a book");
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/delete.png"));
        Image i8 = i7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        deleteButton.setIcon(i9);

        table = new JTable();
        table.setDefaultEditor(Object.class, null); // to disable user editing
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 0).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 750, 500);
        scrollPane.setViewportView(table);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 770, 560);
        panel.setBorder(new TitledBorder(new LineBorder(Color.yellow, 2), "Book Details", TitledBorder.CENTER, TitledBorder.TOP));
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);

        setTable();
        setColumnWidth();
    }

    private void createButton(JButton btn, int a, int b, int c, int d) {
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        btn.setBounds(a, b, c, d);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    private void setTable() {
        try {
            Conn con = new Conn();
            String sql = "select * from book;";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setColumnWidth() {
        try {
            table.setRowHeight(20);
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(200);
            columnModel.getColumn(2).setPreferredWidth(140);
            columnModel.getColumn(3).setPreferredWidth(140);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(5).setPreferredWidth(30);
            columnModel.getColumn(6).setPreferredWidth(40);
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == backButton) {
                dispose();
                new Home(useridString).setVisible(true);

            } else if (ae.getSource() == searchButton) {
                Conn con = new Conn();
                String sql = "select * from book where concat(book_id, book_name, writer, publisher, isbn, edition, page) like ?;";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, "%" + search.getText() + "%");
                ResultSet rs = st.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                setColumnWidth();
                rs.close();
                st.close();
                con.c.close();

            } else if (ae.getSource() == deleteButton) {

                if (search.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Enter book id or name", "Failed", JOptionPane.ERROR_MESSAGE);
                } else {

                    Conn con = new Conn();
                    String sql = "select * from book where book_id = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, search.getText());
                    ResultSet rs = st.executeQuery();

                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(this, "Can't Find Book", "Failed", JOptionPane.ERROR_MESSAGE);
                    } else {

//                        JDialog.setDefaultLookAndFeelDecorated(true);
                        int response = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            sql = "delete from book where book_id = ? or book_name = ?;";
                            st = con.c.prepareStatement(sql);
                            st.setString(1, search.getText());
                            st.setString(2, search.getText());
                            int r = st.executeUpdate();

                            if (r == 1) {
                                JOptionPane.showMessageDialog(this, "Book Successfully Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                                sql = "select * from book;";
                                st = con.c.prepareStatement(sql);
                                rs = st.executeQuery();
                                table.setModel(DbUtils.resultSetToTableModel(rs));
                                setColumnWidth();

                            } else {
                                JOptionPane.showMessageDialog(this, "Can't Find Book", "Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    rs.close();
                    st.close();
                    con.c.close();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Book Details", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BookDetails("").setVisible(true);
    }

}
