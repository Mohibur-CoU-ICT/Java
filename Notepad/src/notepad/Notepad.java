package notepad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;

public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    JScrollPane pane;
    String copiedText;
    boolean isBold = false;
    boolean isItalic = false;
    UndoManager um = new UndoManager();

    Notepad() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(0, 0, 1366, 735);
        setTitle("Simple Notepad");
        
//        System.out.println(getExtendedState()+" "+Frame.ICONIFIED);

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newDoc = new JMenuItem("New");
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newDoc.addActionListener(this);
        newDoc.setMnemonic(KeyEvent.VK_N);
        file.add(newDoc);

        JMenuItem newWindow = new JMenuItem("New Window");
        newWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        newWindow.addActionListener(this);
        file.add(newWindow);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        file.add(open);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        file.add(save);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        file.add(print);

        JMenuItem quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quit.addActionListener(this);
        file.add(quit);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        file.add(exit);

        JMenu edit = new JMenu("Edit");

        JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.addActionListener(this);
        edit.add(undo);

        JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        redo.addActionListener(this);
        edit.add(redo);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        edit.add(paste);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        edit.add(cut);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);
        edit.add(selectAll);

        JMenuItem find = new JMenuItem("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        find.addActionListener(this);
        edit.add(find);

        JMenuItem replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        replace.addActionListener(this);
        edit.add(replace);

        JMenuItem goTo = new JMenuItem("Goto");
        goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        goTo.addActionListener(this);
        edit.add(goTo);

        JMenu format = new JMenu("Format");

        JMenuItem wordWrap = new JMenuItem("Word Wrap");
        wordWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        wordWrap.addActionListener(this);
        format.add(wordWrap);

        JMenuItem bold = new JMenuItem("Bold");
        bold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        bold.addActionListener(this);
        format.add(bold);

        JMenuItem italic = new JMenuItem("Italic");
        italic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        italic.addActionListener(this);
        format.add(italic);

        JMenuItem boldItalic = new JMenuItem("Bold + Italic");
        boldItalic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        boldItalic.addActionListener(this);
        format.add(boldItalic);

        JMenu view = new JMenu("View");

        JMenu zoom = new JMenu("Zoom");

        JMenuItem zoomIn = new JMenuItem("Zoom In");
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));
        zoomIn.addActionListener(this);
        zoom.add(zoomIn);

        JMenuItem zoomOut = new JMenuItem("Zoom Out");
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, ActionEvent.CTRL_MASK));
        zoomOut.addActionListener(this);
        zoom.add(zoomOut);

        JMenuItem zoomDefault = new JMenuItem("Restore Default Zoom");
        zoomDefault.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, ActionEvent.CTRL_MASK));
        zoomDefault.addActionListener(this);
        zoom.add(zoomDefault);

        view.add(zoom);

        JMenu help = new JMenu("Help");

        JMenuItem aboutNotepad = new JMenuItem("About Notepad");
        aboutNotepad.addActionListener(this);
        help.add(aboutNotepad);

        JMenuItem aboutDeveloper = new JMenuItem("About Developer");
        aboutDeveloper.addActionListener(this);
        help.add(aboutDeveloper);

        // adding menus to manubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(view);
        menubar.add(help);

//        add(menubar);
        setJMenuBar(menubar);

        // setting font of JMenu and JMenuItem
        Font menuFont = new Font("Viner Hand ITC", Font.PLAIN, 12);
        for (int i = 0; i < menubar.getMenuCount(); i++) {
            menubar.getMenu(i).setFont(menuFont);
            for (int j = 0; j < menubar.getMenu(i).getMenuComponentCount(); j++) {
                menubar.getMenu(i).getMenuComponent(j).setFont(menuFont);
            }
        }
        for (int i = 0; i < zoom.getMenuComponentCount(); i++) {
            zoom.getMenuComponent(i).setFont(menuFont);
        }

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        area.getDocument().addUndoableEditListener((UndoableEditEvent uee) -> {
            um.addEdit(uee.getEdit());
        });

        pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBorder(BorderFactory.createEmptyBorder());

        add(pane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        System.out.println(ae.getActionCommand());
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("New Window")) {
            new Notepad().setVisible(true);
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt file", "txt");
            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {

            }
        } else if (ae.getActionCommand().equals("Save")) {
            JFileChooser save = new JFileChooser();
            save.setApproveButtonText("Save");

            int action = save.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(save.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {

            }
        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {

            }
        } else if (ae.getActionCommand().equals("Quit")) {
            dispose();
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Undo")) {
            if (um.canUndo()) {
                um.undo();
            }
        } else if (ae.getActionCommand().equals("Redo")) {
            if (um.canRedo()) {
                um.redo();
            }
        } else if (ae.getActionCommand().equals("Copy")) {
            copiedText = area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(copiedText, area.getCaretPosition());
        } else if (ae.getActionCommand().equals("Cut")) {
            copiedText = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        } else if (ae.getActionCommand().equals("Find")) {
            new Find(area).setVisible(true);
        } else if (ae.getActionCommand().equals("Replace")) {
            new Replace(area).setVisible(true);
        } else if (ae.getActionCommand().equals("Goto")) {
            new Goto(area).setVisible(true);
        } else if (ae.getActionCommand().equals("Bold")) {
//            System.out.println("{B, I} : {" + isBold + " " + isItalic + "} " + ae.getActionCommand());
            int fontSize = area.getFont().getSize();
            if (isBold && isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.ITALIC, fontSize));
                isBold = false;
            } else if (isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD + Font.ITALIC, fontSize));
                isBold = true;
            } else if (isBold) {
                area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, fontSize));
                isBold = false;
            } else {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD, fontSize));
                isBold = true;
            }
        } else if (ae.getActionCommand().equals("Italic")) {
//            System.out.println("{B, I} : {" + isBold + " " + isItalic + "} " + ae.getActionCommand());
            int fontSize = area.getFont().getSize();
            if (isBold && isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD, fontSize));
                isItalic = false;
            } else if (isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, fontSize));
                isItalic = false;
            } else if (isBold) {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD + Font.ITALIC, fontSize));
                isItalic = true;
            } else {
                area.setFont(new Font(area.getFont().getFamily(), Font.ITALIC, fontSize));
                isItalic = true;
            }
        } else if (ae.getActionCommand().equals("Bold + Italic")) {
//            System.out.println("{B, I} : {" + isBold + " " + isItalic + "} " + ae.getActionCommand());
            int fontSize = area.getFont().getSize();
            if (isBold && isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, fontSize));
                isBold = false;
                isItalic = false;
            } else if (isItalic) {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD, fontSize));
                isBold = true;
                isItalic = false;
            } else if (isBold) {
                area.setFont(new Font(area.getFont().getFamily(), Font.ITALIC, fontSize));
                isItalic = true;
            } else {
                area.setFont(new Font(area.getFont().getFamily(), Font.BOLD + Font.ITALIC, fontSize));
                isBold = true;
                isItalic = true;
            }
        } else if (ae.getActionCommand().equals("Word Wrap")) {
            if (area.getLineWrap()) {
                area.setLineWrap(false);
            } else {
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
            }
        } else if (ae.getActionCommand().equals("Zoom In")) {
            int fontSize = area.getFont().getSize();
            area.setFont(new Font(area.getFont().getFamily(), area.getFont().getStyle(), fontSize + 1));
        } else if (ae.getActionCommand().equals("Zoom Out")) {
            int fontSize = area.getFont().getSize();
            if (fontSize > 1) {
                area.setFont(new Font(area.getFont().getFamily(), area.getFont().getStyle(), fontSize - 1));
            }
        } else if (ae.getActionCommand().equals("Restore Default Zoom")) {
            area.setFont(new Font(area.getFont().getFamily(), area.getFont().getStyle(), 14));
        } else if (ae.getActionCommand().equals("About Notepad")) {
            new AboutNotepad().setVisible(true);
        } else if (ae.getActionCommand().equals("About Developer")) {
            new AboutDeveloper().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

}
