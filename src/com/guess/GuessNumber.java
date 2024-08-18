package com.guess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessNumber extends JFrame {
    private int randomNumber;
    private int attemptsLeft;
    private int maxAttempts = 5;

    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JButton newGameButton;
    private JLabel attemptsLabel;
    private GradientPanel visualizationPanel;

    public GuessNumber() {
        // Set up the frame
        setTitle("Number Guessing Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Initialize components
        promptLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("", JLabel.CENTER);
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        newGameButton = new JButton("New Game");
        visualizationPanel = new GradientPanel();

        // Set up layout and add padding
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(promptLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(feedbackLabel);
        inputPanel.add(attemptsLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(visualizationPanel, BorderLayout.SOUTH);
        add(newGameButton, BorderLayout.NORTH);

        // Set preferred sizes for buttons
        guessButton.setPreferredSize(new Dimension(100, 30));
        newGameButton.setPreferredSize(new Dimension(100, 30));

        // Set up action listeners
        guessButton.addActionListener(new GuessButtonListener());
        newGameButton.addActionListener(new NewGameButtonListener());

        // Start a new game
        startNewGame();
    }

    private void startNewGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attemptsLeft = maxAttempts;
        guessField.setText("");
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
        visualizationPanel.setGuessPosition(-1);
        visualizationPanel.setRandomNumber(randomNumber);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                attemptsLeft--;

                if (userGuess == randomNumber) {
                    feedbackLabel.setText("Correct! The number was " + randomNumber);
                    guessButton.setEnabled(false);
                    newGameButton.setEnabled(true);
                } else if (userGuess < randomNumber) {
                    feedbackLabel.setText("Too low!");
                } else {
                    feedbackLabel.setText("Too high!");
                }

                attemptsLabel.setText("Attempts left: " + attemptsLeft);

                if (attemptsLeft == 0 && userGuess != randomNumber) {
                    feedbackLabel.setText("Out of attempts! The number was " + randomNumber);
                    guessButton.setEnabled(false);
                    newGameButton.setEnabled(true);
                }

                visualizationPanel.setGuessPosition(userGuess);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    private static class GradientPanel extends JPanel {
        private int guessPosition = -1;
        private int randomNumber = -1;

        public void setGuessPosition(int guess) {
            this.guessPosition = guess;
            repaint();
        }

        public void setRandomNumber(int randomNumber) {
            this.randomNumber = randomNumber;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw gradient
            int width = getWidth();
            int height = getHeight();
            int middle = randomNumber * width / 100;
            GradientPaint gradient = new GradientPaint(0, 0, Color.GREEN, middle, 0, Color.YELLOW);
            GradientPaint gradient2 = new GradientPaint(middle, 0, Color.YELLOW, width, 0, Color.RED);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, middle, height);
            g2d.setPaint(gradient2);
            g2d.fillRect(middle, 0, width, height);

            // Draw guess marker
            if (guessPosition != -1) {
                int markerX = (int) ((guessPosition / 100.0) * width);
                g2d.setColor(Color.BLACK);
                g2d.fillPolygon(new int[]{markerX - 5, markerX + 5, markerX}, new int[]{height - 10, height - 10, height}, 3);
                g2d.drawString("Your Guess: " + guessPosition, markerX - 25, height - 15);
            }

            // Draw labels
            g2d.setColor(Color.BLACK);
            g2d.drawString("Low", 5, height - 5);
            g2d.drawString("High", width - 30, height - 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessNumber game = new GuessNumber();
            game.setVisible(true);
        });
    }
}
