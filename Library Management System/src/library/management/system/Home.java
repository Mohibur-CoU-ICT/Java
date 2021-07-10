package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Home extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JButton addBook, addStudent, issueBook, returnBook, statistics, profile;
    String useridString;

    Home(String useridString) {
        this.useridString = useridString;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Home");
        setSize(800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JMenuBar menubar = new JMenuBar();
        menubar.setBounds(0, 0, 800, 20);
        contentPane.add(menubar);

        JMenu record = new JMenu("Record");
        menubar.add(record);

        JMenuItem bookDetails = new JMenuItem("Book Details");
        bookDetails.addActionListener(this);
        record.add(bookDetails);

        JMenuItem studentDetails = new JMenuItem("Student Details");
        studentDetails.addActionListener(this);
        record.add(studentDetails);

        JMenu help = new JMenu("Help");
        menubar.add(help);

        JMenuItem howToUse = new JMenuItem("How To Use");
        howToUse.addActionListener(this);
        help.add(howToUse);

        JMenu about = new JMenu("About");
        menubar.add(about);

        JMenuItem aboutApp = new JMenuItem("About App");
        aboutApp.addActionListener(this);
        about.add(aboutApp);

        JMenuItem aboutDeveloper = new JMenuItem("About Developer");
        aboutDeveloper.addActionListener(this);
        about.add(aboutDeveloper);

        JMenu Exit = new JMenu("Exit");
        menubar.add(Exit);

        JMenuItem logout = new JMenuItem("Log out");
        logout.addActionListener(this);
        Exit.add(logout);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        Exit.add(exit);

        JLabel l1 = new JLabel("Library Management System");
        l1.setBounds(180, 15, 450, 40);
        l1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 30));
        l1.setForeground(new Color(200, 50, 100));
        contentPane.add(l1);

        ImageIcon addBookii = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/add book.png"));
        createImageIcon(addBookii, 155, 100, 100, 100);

        addBook = new JButton("Add Book");
        createButton(addBook, 150, 220, 110, 25);

        ImageIcon addStudentii = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/add student.png"));
        createImageIcon(addStudentii, 355, 100, 100, 100);

        addStudent = new JButton("Add Student");
        createButton(addStudent, 350, 220, 110, 25);

        ImageIcon statisticsii = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/statistics.png"));
        createImageIcon(statisticsii, 545, 100, 100, 100);

        statistics = new JButton("Statistics");
        createButton(statistics, 540, 220, 110, 25);

        JPanel operation = new JPanel();
        operation.setBorder(new TitledBorder(new LineBorder(new Color(255, 127, 63), 2), "Operation",
                TitledBorder.LEADING, TitledBorder.TOP));
        operation.setBounds(100, 70, 600, 200);
        operation.setBackground(Color.WHITE);
        contentPane.add(operation);

        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/issue book.png"));
        createImageIcon(ii1, 155, 330, 100, 100);

        issueBook = new JButton("Issue Book");
        createButton(issueBook, 150, 450, 110, 25);

        ImageIcon ii4 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/return book.png"));
        createImageIcon(ii4, 355, 330, 100, 100);

        returnBook = new JButton("Return Book");
        createButton(returnBook, 350, 450, 110, 25);

        ImageIcon ii7 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/profile.png"));
        createImageIcon(ii7, 545, 330, 100, 100);

        profile = new JButton("Profile");
        createButton(profile, 540, 450, 110, 25);

        JPanel action = new JPanel();
        action.setBorder(new TitledBorder(new LineBorder(new Color(63, 127, 255), 2), "Action",
                TitledBorder.LEADING, TitledBorder.TOP));
        action.setBounds(100, 300, 600, 200);
        action.setBackground(Color.WHITE);
        contentPane.add(action);
    }

    private void createButton(JButton btn, int a, int b, int c, int d) {
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        btn.setBounds(a, b, c, d);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    private void createImageIcon(ImageIcon ii, int a, int b, int c, int d) {
        Image i1 = ii.getImage().getScaledInstance(c, d, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(a, b, c, d);
        contentPane.add(l1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        switch (command) {
            case "Book Details":
                dispose();
                new BookDetails(useridString).setVisible(true);
                break;
            case "Student Details":
                dispose();
                new StudentDetails(useridString).setVisible(true);
                break;
            case "How To Use":
                new HowToUse().setVisible(true);
                break;
            case "About App":
                new AboutApp().setVisible(true);
                break;
            case "About Developer":
                new AboutDeveloper().setVisible(true);
                break;
            case "Log out":
                dispose();
                new Login().setVisible(true);
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Add Book":
                dispose();
                new AddBook(useridString).setVisible(true);
                break;
            case "Add Student":
                dispose();
                new AddStudent(useridString).setVisible(true);
                break;
            case "Statistics":
                dispose();
                new Statistics(useridString).setVisible(true);
                break;
            case "Issue Book":
                dispose();
                new IssueBook(useridString).setVisible(true);
                break;
            case "Return Book":
                dispose();
                new ReturnBook(useridString).setVisible(true);
                break;
            case "Profile":
                dispose();
                new Profile(useridString).setVisible(true);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Home("").setVisible(true);
    }
}
