package com.quiz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizSubjects {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizSubjects window = new QuizSubjects();
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
	public QuizSubjects() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(112, 128, 144));
		frame.setBounds(100, 100, 451, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel mysqlPanel = new JPanel();
		mysqlPanel.setBackground(new Color(230, 230, 250));
		mysqlPanel.setBounds(226, 159, 172, 91);
		frame.getContentPane().add(mysqlPanel);
		mysqlPanel.setLayout(null);
		
		JLabel lblNewLabel_2_4 = new JLabel("10    |    10 min");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_4.setBounds(23, 44, 122, 22);
		mysqlPanel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Questions       Time");
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2_1_3.setBounds(10, 58, 122, 22);
		mysqlPanel.add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("MySQL");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(32, 11, 100, 22);
		mysqlPanel.add(lblNewLabel_1_3);
		
		JPanel cPanel = new JPanel();
		cPanel.setBackground(new Color(230, 230, 250));
		cPanel.setBounds(37, 159, 172, 91);
		frame.getContentPane().add(cPanel);
		cPanel.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("10    |    10 min");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_3.setBounds(23, 44, 122, 22);
		cPanel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Questions       Time");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2_1_2.setBounds(10, 58, 122, 22);
		cPanel.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("C");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(32, 11, 100, 22);
		cPanel.add(lblNewLabel_1_2);
		
		JPanel javaPanel = new JPanel();
		javaPanel.setBackground(new Color(230, 230, 250));
		javaPanel.setBounds(37, 57, 172, 91);
		frame.getContentPane().add(javaPanel);
		javaPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("JAVA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(36, 11, 100, 22);
		javaPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("10    |    10 min");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(27, 44, 122, 22);
		javaPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Questions       Time");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2_1.setBounds(14, 58, 122, 22);
		javaPanel.add(lblNewLabel_2_1);
		
		JPanel pythonPanel = new JPanel();
		pythonPanel.setBackground(new Color(230, 230, 250));
		pythonPanel.setBounds(226, 57, 172, 91);
		frame.getContentPane().add(pythonPanel);
		pythonPanel.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("10    |    10 min");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(23, 44, 122, 22);
		pythonPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Questions       Time");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2_1_1.setBounds(10, 58, 122, 22);
		pythonPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PYTHON");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(32, 11, 100, 22);
		pythonPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Quiz Subjects");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 11, 193, 28);
		frame.getContentPane().add(lblNewLabel);
		
//		Panel Code
		
		javaPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        QuizQuestions que = new QuizQuestions("JAVA");
		        que.getFrame().setVisible(true);
		        frame.setVisible(false);
		    }
		});

		pythonPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        QuizQuestions que = new QuizQuestions("PYTHON");
		        que.getFrame().setVisible(true);
		        frame.setVisible(false);
		    }
		});

		cPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        QuizQuestions que = new QuizQuestions("C");
		        que.getFrame().setVisible(true);
		        frame.setVisible(false);
		    }
		});

		mysqlPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        QuizQuestions que = new QuizQuestions("MYSQL");
		        que.getFrame().setVisible(true);
		        frame.setVisible(false);
		    }
		});
	}

	public JFrame getFrame() {
		return frame;
		
	}
	

}
