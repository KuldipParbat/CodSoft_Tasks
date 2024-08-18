package com.quiz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

import com.quiz.entity.Question;
import java.awt.Font;
import java.awt.Color;

public class QuizQuestions {

    private JFrame frame;
    private static final int TWO_MINUTES = 2* 60 * 1000; // 10 minutes in milliseconds
    private static final int ONE_SECOND = 1000; // 1 second in milliseconds
    private long startTime;
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;
    private JButton backButton;
    private int currentQuestionIndex = 0;
    private static List<Question> questions;
    private int score = 0;
    private List<Question> randomQuestions;
    private String subject;
    private int[] userAnswers;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuizQuestions window = new QuizQuestions();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public QuizQuestions() {
        initialize();
    }

    public QuizQuestions(String subject) {
        this.subject = subject;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(112, 128, 144));
        panel.setBounds(0, 0, 434, 261);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        timerLabel = new JLabel("Time remaining: 2:00", SwingConstants.CENTER);
        timerLabel.setBounds(0, 0, 434, 30);
        panel.add(timerLabel);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setBounds(50, 41, 384, 30);
        panel.add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 80 + (i * 30), 300, 30);
            group.add(options[i]);
            panel.add(options[i]);
        }

        nextButton = new JButton("NEXT");
        nextButton.setBounds(258, 203, 166, 47);
        panel.add(nextButton);

        backButton = new JButton("BACK");
        backButton.setEnabled(false);
        backButton.setBounds(10, 203, 166, 47);
        panel.add(backButton);

        JLabel numberLabel_1 = new JLabel("1", SwingConstants.CENTER);
        numberLabel_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        numberLabel_1.setBounds(10, 41, 30, 30);
        panel.add(numberLabel_1);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    loadQuestion();
                    String srNo = String.valueOf(currentQuestionIndex + 1);
                    numberLabel_1.setText(srNo);

                    if (currentQuestionIndex == 0) {
                        backButton.setEnabled(false);
                    }

                    nextButton.setText("NEXT");
                    nextButton.setEnabled(true);
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                String srNo = String.valueOf(currentQuestionIndex + 1);
                
                numberLabel_1.setText(srNo);
                
                if(srNo.equals("11")) {
                	numberLabel_1.setText("10");
                }
                
                
                if (currentQuestionIndex < randomQuestions.size()) {
                    loadQuestion();
                } else {
                    JOptionPane.showMessageDialog(frame, "End of Quiz. Your score: " + score);
                    nextButton.setEnabled(false);
                    backButton.setEnabled(true);
                    // Close the application after score is shown and user clicks OK
                    frame.dispose();
                }

                if (currentQuestionIndex == randomQuestions.size() - 1) {
                    nextButton.setText("SUBMIT");
                }

                if (currentQuestionIndex > 0) {
                    backButton.setEnabled(true);
                }
            }
        });

        questions = new ArrayList<>();
        addQuestionsBasedOnSubject(); // Adds questions based on the selected subject

        randomQuestions = getRandomQuestions(questions, 10);

        userAnswers = new int[randomQuestions.size()];
        Arrays.fill(userAnswers, -1); // Initialize with -1, meaning no answer selected

        loadQuestion();

        startTime = System.currentTimeMillis();
        Timer timer = new Timer(ONE_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - startTime;
                long remaining = TWO_MINUTES - elapsed;

                if (remaining > 0) {
                    long minutes = (remaining / 1000) / 60;
                    long seconds = (remaining / 1000) % 60;
                    timerLabel.setText(String.format("Time remaining: %02d:%02d", minutes, seconds));
                } else {
                    timerLabel.setText("Time is up!");
                    ((Timer) e.getSource()).stop();
                    checkAnswer(); // Ensure final answers are checked when time is up
                    JOptionPane.showMessageDialog(frame, "Time is up! Your final score: " + score);
                    // Close the application after score is shown and user clicks OK
                    frame.dispose();
                }
            }
        });

        timer.start();
    }

    private void loadQuestion() {
        Question currentQuestion = randomQuestions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getQuestion());
        String[] optionsText = currentQuestion.getOptions();
        group.clearSelection(); // Clear previous selection
        for (int i = 0; i < options.length; i++) {
            options[i].setText(optionsText[i]);
            options[i].setSelected(false);
        }

        // Restore the previously selected answer, if any
        if (userAnswers[currentQuestionIndex] != -1) {
            options[userAnswers[currentQuestionIndex]].setSelected(true);
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                userAnswers[currentQuestionIndex] = i;
                if (i == randomQuestions.get(currentQuestionIndex).getCorrectAnswer()) {
                    score++;
                }
                break;
            }
        }
    }

    private List<Question> getRandomQuestions(List<Question> questions, int numberOfQuestions) {
        List<Question> shuffledQuestions = new ArrayList<>(questions);
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions.subList(0, Math.min(numberOfQuestions, shuffledQuestions.size()));
    }

    private void addQuestionsBasedOnSubject() {
        String subject = this.subject;
        switch (subject) {
            case "JAVA":
                addJavaQuestions();
                break;
            case "PYTHON":
                addPythonQuestions();
                break;
            case "C":
                addCQuestions();
                break;
            case "MYSQL":
                addMysqlQuestions();
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid subject! Defaulting to JAVA.");
                addJavaQuestions();
        }
    }

    private void addJavaQuestions() {
        questions.add(new Question("Which of the following is not a primitive data type in Java?", new String[]{"int", "float", "boolean", "String"}, 3));
        questions.add(new Question("What is the size of an int variable in Java?", new String[]{"4 bytes", "8 bytes", "2 bytes", "1 byte"}, 0));
        questions.add(new Question("Which method is used to find the length of a string in Java?", new String[]{"length()", "size()", "getLength()", "strlen()"}, 0));
        questions.add(new Question("What is the default value of a boolean variable in Java?", new String[]{"true", "false", "0", "null"}, 1));
        questions.add(new Question("Which of the following is used to handle exceptions in Java?", new String[]{"try-catch", "throw", "throws", "finally"}, 0));
        questions.add(new Question("Which keyword is used to create an object in Java?", new String[]{"class", "new", "object", "this"}, 1));
        questions.add(new Question("What is the output of 5 + 2 + '8' in Java?", new String[]{"15", "58", "528", "None of the above"}, 1));
        questions.add(new Question("Which of the following is not a valid access modifier in Java?", new String[]{"private", "public", "protected", "package"}, 3));
        questions.add(new Question("What is the purpose of the 'final' keyword in Java?", new String[]{"To create a constant", "To define a method that cannot be overridden", "To define a class that cannot be subclassed", "All of the above"}, 3));
        questions.add(new Question("What does the term 'polymorphism' mean in Java?", new String[]{"Many forms", "One form", "Inheritance", "Encapsulation"}, 0));
    }

    private void addPythonQuestions() {
        questions.add(new Question("Which of the following is not a keyword in Python?", new String[]{"lambda", "if", "eval", "pass"}, 2));
        questions.add(new Question("What is the output of the expression '2 ** 3' in Python?", new String[]{"5", "6", "8", "9"}, 2));
        questions.add(new Question("Which function is used to read input from the user in Python?", new String[]{"scanf()", "cin", "input()", "read()"}, 2));
        questions.add(new Question("Which data type is used to represent text in Python?", new String[]{"int", "float", "str", "char"}, 2));
        questions.add(new Question("What is the correct file extension for Python files?", new String[]{".java", ".py", ".txt", ".cpp"}, 1));
        questions.add(new Question("What is the output of '3 * 3' in Python?", new String[]{"6", "9", "12", "None of the above"}, 1));
        questions.add(new Question("Which of the following is used to define a function in Python?", new String[]{"func", "function", "def", "define"}, 2));
        questions.add(new Question("Which of the following is not a core data type in Python?", new String[]{"Lists", "Tuples", "Dictionaries", "Class"}, 3));
        questions.add(new Question("What does the 'break' keyword do in Python?", new String[]{"Exits the current loop", "Restarts the current loop", "Continues to the next iteration of the loop", "None of the above"}, 0));
        questions.add(new Question("Which operator is used for string concatenation in Python?", new String[]{"+", "*", "&", "++"}, 0));
    }

    private void addCQuestions() {
        questions.add(new Question("Which of the following is not a valid C variable name?", new String[]{"int", "float", "main", "_main"}, 0));
        questions.add(new Question("What is the size of an int variable in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on the compiler"}, 1));
        questions.add(new Question("Which of the following is used to start a loop in C?", new String[]{"if", "else", "for", "switch"}, 2));
        questions.add(new Question("What is the correct syntax to print a string in C?", new String[]{"print()", "printf()", "println()", "cout"}, 1));
        questions.add(new Question("What is the default value of an int variable in C?", new String[]{"0", "1", "Garbage value", "NULL"}, 2));
        questions.add(new Question("Which operator is used to allocate memory dynamically in C?", new String[]{"malloc", "calloc", "new", "Both malloc and calloc"}, 3));
        questions.add(new Question("What is the output of 'printf(\"%d\", 5 + 3);' in C?", new String[]{"5", "8", "53", "None of the above"}, 1));
        questions.add(new Question("Which of the following is not a valid storage class in C?", new String[]{"auto", "static", "register", "volatile"}, 3));
        questions.add(new Question("What does the term 'pointer' mean in C?", new String[]{"A variable that stores the address of another variable", "A function that points to a memory location", "A data type used to allocate memory", "None of the above"}, 0));
        questions.add(new Question("Which of the following is not a valid loop in C?", new String[]{"for", "while", "do-while", "foreach"}, 3));
    }

    private void addMysqlQuestions() {
        questions.add(new Question("Which SQL statement is used to retrieve data from a database?", new String[]{"SELECT", "INSERT", "UPDATE", "DELETE"}, 0));
        questions.add(new Question("Which of the following is a MySQL database?", new String[]{"Oracle", "MS SQL", "MongoDB", "InnoDB"}, 3));
        questions.add(new Question("Which command is used to delete a table from a database?", new String[]{"DELETE TABLE", "DROP TABLE", "REMOVE TABLE", "TRUNCATE TABLE"}, 1));
        questions.add(new Question("Which keyword is used to sort the result set in ascending order?", new String[]{"ORDER BY ASC", "SORT ASC", "ORDER ASC", "ASC ORDER"}, 0));
        questions.add(new Question("Which function is used to count the number of rows in a table?", new String[]{"COUNT_ROWS()", "ROW_COUNT()", "COUNT()", "ROWS_COUNT()"}, 2));
        questions.add(new Question("Which SQL clause is used to filter the results?", new String[]{"WHERE", "FILTER", "GROUP BY", "HAVING"}, 0));
        questions.add(new Question("Which operator is used to search for a specified pattern in a column?", new String[]{"LIKE", "IN", "BETWEEN", "SEARCH"}, 0));
        questions.add(new Question("Which SQL statement is used to create a new table?", new String[]{"CREATE TABLE", "MAKE TABLE", "NEW TABLE", "ADD TABLE"}, 0));
        questions.add(new Question("Which of the following is a valid aggregate function?", new String[]{"SUM()", "MIN()", "MAX()", "All of the above"}, 3));
        questions.add(new Question("Which SQL command is used to modify data in a table?", new String[]{"ALTER", "UPDATE", "MODIFY", "CHANGE"}, 1));
    }


	public JFrame getFrame() {
		return frame;
	}
}
