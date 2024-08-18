package com.guess;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class NumberGuess {

    private JFrame frame;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JLabel feedbackLabel;
    private JLabel roundsLabel;
    private JLabel scoreLabel;
    private JButton btnGuess;
    private JButton btnTryAgain;
    private int randomNumber;
    private int attemptsLeft;
    private int maxAttempts = 5;
    private int score = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NumberGuess window = new NumberGuess();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NumberGuess() {
        initialize();
        startNewGame();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 200);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(142, 97, 126, 28);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("GUESS NUMBER");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(108, 23, 205, 40);
        panel.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Range : 1 to 100");
        lblNewLabel_1.setBounds(161, 72, 96, 14);
        panel.add(lblNewLabel_1);

        roundsLabel = new JLabel("Rounds remaining: " + attemptsLeft);
        roundsLabel.setBounds(152, 117, 200, 30);
        panel.add(roundsLabel);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scoreLabel.setBounds(171, 0, 150, 30);
        panel.add(scoreLabel);
        

        btnTryAgain = new JButton("TRY AGAIN");
        btnTryAgain.setBounds(230, 169, 110, 31);
        panel.add(btnTryAgain);
        btnTryAgain.setEnabled(false);

        btnTryAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
                btnGuess.setEnabled(true);
                btnTryAgain.setEnabled(false);
                score=0;
                scoreLabel.setText("Score: " + score);
                
            }
        });

        btnGuess = new JButton("GUESS");
        btnGuess.setBounds(103, 169, 76, 31);
        panel.add(btnGuess);
        
                feedbackLabel = new JLabel("");
                feedbackLabel.setBounds(10, 211, 414, 30);
                frame.getContentPane().add(feedbackLabel);

        btnGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(textField.getText());
                    attemptsLeft--;

                    if (userGuess == randomNumber) {
                        feedbackLabel.setText("Correct! The number was " + randomNumber);
                        btnGuess.setEnabled(false);
                        btnTryAgain.setEnabled(true);
                        score += attemptsLeft+5; // Add remaining attempts to score
                        scoreLabel.setEnabled(true);
                    } else if (userGuess < randomNumber) {
                        int percentage = calculatePercentage(userGuess, randomNumber);
                        feedbackLabel.setText("Too low! " + percentage + "% low.");
                    } else {
                        int percentage = calculatePercentage(userGuess, randomNumber);
                        feedbackLabel.setText("Too high! " + percentage + "% high.");
                    }

                    if (attemptsLeft == 0 && userGuess != randomNumber) {
                        feedbackLabel.setText("Out of attempts! The number was " + randomNumber);
                        btnGuess.setEnabled(false);
                        btnTryAgain.setEnabled(true);
                        roundsLabel.setText("No more rounds left.");
                    } else {
                        roundsLabel.setText("Rounds remaining: " + attemptsLeft);
                    }

                    scoreLabel.setText("Score: " + score);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
            }
        });
    }

    private void startNewGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attemptsLeft = maxAttempts;
        textField.setText("");
        feedbackLabel.setText("");
        roundsLabel.setText("Rounds remaining: " + attemptsLeft);
    }

    private static int calculatePercentage(int guess, int target) {
        if (guess == target) {
            return 0;
        } else if (guess < target) {
            return (int) ((double) (target - guess) / target * 100);
        } else {
            return (int) ((double) (guess - target) / target * 100);
        }
    }
}
