
package com.quiz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Color;

public class Quiz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz window = new Quiz();
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
	public Quiz() {
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
		
		ImageIcon originalIcon = new ImageIcon(
				"C:\\Users\\Kuldip\\eclipse_cooldeep\\Tasks\\Image\\quizTime.png");
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(resizedImage));
		logo.setBounds(52, 11, 330, 239);
		panel.add(logo);
		
		
		 Timer timer = new Timer(3000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 QuizSubjects sub = new QuizSubjects();
                 sub.getFrame().setVisible(true);
                 frame.setVisible(false);
                 
             }
         });
         timer.setRepeats(false);  // Only execute once
         timer.start();
	}
}
