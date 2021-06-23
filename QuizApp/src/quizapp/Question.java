package quizapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.Timer;

class EachQuestion {

    int questionNo;
    int correctOption;
    String questionText;
    ArrayList<String> options = new ArrayList<>();
}

public class Question extends JFrame implements ActionListener {

    ArrayList<EachQuestion> allQuestions = new ArrayList<>();
    JLabel questionTextJLabel, answerLogJLabel, timerJLabel;
    ArrayList<Integer> questionLog, answerLog;
    JButton nextButton, exitButton;
    JRadioButton[] fiveOption;
    boolean visitedQuestion[];
    int currentQuestion;
    int correctAnswer;
    int hours = 0;
    int minutes = 10;
    int seconds = 0;
    Timer timer;

    void putAllQuestions() {

        EachQuestion eachQuestion = new EachQuestion();

        eachQuestion.questionNo = 0;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "Which one among these is not a primitive datatype?";
        eachQuestion.options.add("int");
        eachQuestion.options.add("Float");
        eachQuestion.options.add("boolean");
        eachQuestion.options.add("char");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 1;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which class is available to all the class automatically?";
        eachQuestion.options.add("Swing");
        eachQuestion.options.add("Applet");
        eachQuestion.options.add("Object");
        eachQuestion.options.add("ActionEvent");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 2;
        eachQuestion.correctOption = 3;
        eachQuestion.questionText = "Which package is directly available to our class without importing it?";
        eachQuestion.options.add("swing");
        eachQuestion.options.add("applet");
        eachQuestion.options.add("net");
        eachQuestion.options.add("lang");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 3;
        eachQuestion.correctOption = 0;
        eachQuestion.questionText = "String class is defined in which package?";
        eachQuestion.options.add("lang");
        eachQuestion.options.add("swing");
        eachQuestion.options.add("applet");
        eachQuestion.options.add("awt");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 4;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which one among these is not a keyword?";
        eachQuestion.options.add("class");
        eachQuestion.options.add("int");
        eachQuestion.options.add("get");
        eachQuestion.options.add("if");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 5;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "Which one among these is not a class?";
        eachQuestion.options.add("Swing");
        eachQuestion.options.add("Actionperformed");
        eachQuestion.options.add("ActionEvent");
        eachQuestion.options.add("Button");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 6;
        eachQuestion.correctOption = 3;
        eachQuestion.questionText = "Which one among these is not a function of Object class?";
        eachQuestion.options.add("toString");
        eachQuestion.options.add("finalize");
        eachQuestion.options.add("equals");
        eachQuestion.options.add("getDocumentBase");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 7;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "Which function is not present in Applet class?";
        eachQuestion.options.add("init");
        eachQuestion.options.add("main");
        eachQuestion.options.add("start");
        eachQuestion.options.add("destroy");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 8;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which one among these is not a valid component?";
        eachQuestion.options.add("JButton");
        eachQuestion.options.add("JList");
        eachQuestion.options.add("JButtonGroup");
        eachQuestion.options.add("JTextArea");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 9;
        eachQuestion.correctOption = 0;
        eachQuestion.questionText = "Which of this keyword can be used in a subclass to call the constructor <br>of superclass?";
        eachQuestion.options.add("super");
        eachQuestion.options.add("this");
        eachQuestion.options.add("extent");
        eachQuestion.options.add("extends");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 10;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "What is the process of defining a method in a subclass having same <br>name & type signature as a method in its superclass?";
        eachQuestion.options.add("Method overloading");
        eachQuestion.options.add("Method overriding");
        eachQuestion.options.add("Method hiding");
        eachQuestion.options.add("None of the mentioned");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 11;
        eachQuestion.correctOption = 3;
        eachQuestion.questionText = "Which of these keywords can be used to prevent Method overriding?";
        eachQuestion.options.add("static");
        eachQuestion.options.add("constant");
        eachQuestion.options.add("protected");
        eachQuestion.options.add("final");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 12;
        eachQuestion.correctOption = 3;
        eachQuestion.questionText = "Which of these is correct way of calling a constructor having no <br>parameters, of superclass A by subclass B?";
        eachQuestion.options.add("super(void);");
        eachQuestion.options.add("superclass.();");
        eachQuestion.options.add("super.A();");
        eachQuestion.options.add("super();");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 13;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which of these is supported by method overriding in Java?";
        eachQuestion.options.add("Abstraction");
        eachQuestion.options.add("Encapsulation");
        eachQuestion.options.add("Polymorphism");
        eachQuestion.options.add("None of the mentioned");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 14;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "Which of these class is superclass of every class in Java?";
        eachQuestion.options.add("String class");
        eachQuestion.options.add("Object class");
        eachQuestion.options.add("Abstract class");
        eachQuestion.options.add("ArrayList class");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 15;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which of these method of Object class can clone an object?";
        eachQuestion.options.add("Objectcopy()");
        eachQuestion.options.add("copy()");
        eachQuestion.options.add("Objectclone()");
        eachQuestion.options.add("clone()");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 16;
        eachQuestion.correctOption = 2;
        eachQuestion.questionText = "Which of these method of Object class is used to obtain class of an <br>object at run time?";
        eachQuestion.options.add("get()");
        eachQuestion.options.add("void getclass()");
        eachQuestion.options.add("Class getclass()");
        eachQuestion.options.add("None of the mentioned");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 17;
        eachQuestion.correctOption = 3;
        eachQuestion.questionText = "Which of these keywords can be used to prevent inheritance of a class?";
        eachQuestion.options.add("super");
        eachQuestion.options.add("constant");
        eachQuestion.options.add("class");
        eachQuestion.options.add("final");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 18;
        eachQuestion.correctOption = 0;
        eachQuestion.questionText = "Which of these keywords cannot be used for a class which has been <br>declared final?";
        eachQuestion.options.add("abstract");
        eachQuestion.options.add("extends");
        eachQuestion.options.add("abstract and extends");
        eachQuestion.options.add("none of the mentioned");
        allQuestions.add(eachQuestion);

        eachQuestion = new EachQuestion();
        eachQuestion.questionNo = 19;
        eachQuestion.correctOption = 1;
        eachQuestion.questionText = "Which of these class relies upon its subclasses for complete <br>implementation of its methods?";
        eachQuestion.options.add("Object class");
        eachQuestion.options.add("Abstract class");
        eachQuestion.options.add("ArrayList class");
        eachQuestion.options.add("None of the mentioned");
        allQuestions.add(eachQuestion);

    }

    void getAllQuestion() {
        for (int i = 0; i < allQuestions.size(); i++) {
            EachQuestion eq = allQuestions.get(i);
            System.out.println(eq.questionNo + " " + eq.questionText);
            for (int j = 0; j < eq.options.size(); j++) {
                System.out.println(eq.options.get(j));
            }
            System.out.println("Correct option = " + eq.correctOption);
        }
    }

    Question() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(388, 150, 600, 450);
        setTitle("Quiz Application");
        setResizable(false);
        setLayout(null);

        putAllQuestions();
//        getAllQuestion();

        this.visitedQuestion = new boolean[allQuestions.size()];
        this.fiveOption = new JRadioButton[5];
        this.questionLog = new ArrayList<>();
        this.answerLog = new ArrayList<>();
        this.currentQuestion = 0;
        this.correctAnswer = 0;

        this.timerJLabel = new JLabel();
        this.timerJLabel.setBounds(450, 10, 150, 30);
        this.timerJLabel.setText("00:10:00");
        this.timerJLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        add(this.timerJLabel);

        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                System.out.println("Reading SMTP Info.");
                String hoursString = String.format("%02d", hours);
                String minutesString = String.format("%02d", minutes);
                String secondsString = String.format("%02d", seconds);
                String remainingTime = hoursString + ":" + minutesString + ":" + secondsString;
                timerJLabel.setText(remainingTime);
                if (hours == 0 && minutes == 0 && seconds == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Times up", "Quiz Application", JOptionPane.INFORMATION_MESSAGE);
                    new Result(createAnswerLog(), correctAnswer).setVisible(true);
                    dispose();
                }
                if (seconds == 0) {
                    if (minutes > 0) {
                        minutes--;
                        seconds = 59;
                    }
                }
                if (minutes == 0) {
                    if (hours > 0) {
                        hours--;
                        minutes = 59;
                    }
                }
                seconds--;
            }
        };

        timer = new Timer(1000, timerListener);
        timer.start();

        questionTextJLabel = new JLabel();
        questionTextJLabel.setBounds(30, 40, 540, 35);
        questionTextJLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(questionTextJLabel);

        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            fiveOption[i] = new JRadioButton();
            bg.add(fiveOption[i]);
            add(fiveOption[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setBounds(250, 350, 100, 30);
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        nextButton.setFocusable(false);
        add(nextButton);

        setQuestion();
    }

    void setQuestion() {
        int ub = allQuestions.size();
        Random rand = new Random();
        int index;
        while (true) {
            index = rand.nextInt(ub);
            if (visitedQuestion[index] == false) {
                break;
            }
        }
        questionLog.add(index);
//        System.out.println(index);

        visitedQuestion[index] = true;
        currentQuestion++;
        EachQuestion eq = allQuestions.get(index);
        questionTextJLabel.setText("<html>" + Integer.toString(currentQuestion) + ". " + eq.questionText + "</html>");
        fiveOption[4].setSelected(true);
        for (int i = 0; i < 4; i++) {
            String optionText = eq.options.get(i);
            fiveOption[i].setSelected(false);
            fiveOption[i].setBounds(50, 90 + i * 30, 30 + 7 * optionText.length(), 20);
            fiveOption[i].setText(optionText);
        }
    }

    boolean checkAnswer() {
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (fiveOption[i].isSelected()) {
                selectedOption = i;
            }
        }
        answerLog.add(selectedOption);
        return (allQuestions.get(questionLog.get(currentQuestion - 1)).correctOption == selectedOption);
    }

    String createAnswerLog() {
        StringBuilder resultLog = new StringBuilder();
        resultLog.append("<html>Your Answer ---> Correct Answer<br>");

        for (int i = 0; i < answerLog.size(); i++) {
            int ithQuestionNo = questionLog.get(i);
            int ithAnswerNo = answerLog.get(i);
            EachQuestion eq = allQuestions.get(ithQuestionNo);
            resultLog.append(Integer.toString(i + 1)).append(". ");
//            System.out.println(ithQuestionNo + " " + ithAnswerNo);

            if (ithAnswerNo != -1 && ithAnswerNo != 4) {
                String yourAnswer = eq.options.get(ithAnswerNo);
                resultLog.append(yourAnswer);
            } else {
                resultLog.append("......");
            }
            resultLog.append(" ---> ");
            resultLog.append(eq.options.get(eq.correctOption));
            resultLog.append("<br>");
        }
        resultLog.append("</html>");

        return resultLog.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Next")) {
            if (checkAnswer()) {
                correctAnswer++;
            }
            if (currentQuestion < 20) {
                setQuestion();
            } else {
//                System.out.println("currentQuestion = " + currentQuestion);
//                System.out.println("answerLog.size() = " + answerLog.size());
//                System.out.println("questionLog.size() = " + questionLog.size());
//                System.out.println("allQuestions.size() = " + allQuestions.size());
                timer.stop();
                new Result(createAnswerLog(), correctAnswer).setVisible(true);
                dispose();
            }
        } else if (e.getActionCommand().equals("Again")) {
            new QuizApp().setVisible(true);
            dispose();
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Question().setVisible(true);
    }
}
