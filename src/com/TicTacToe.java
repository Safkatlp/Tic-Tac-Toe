package com;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;



public class TicTacToe implements ActionListener{
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel info_panel  = new JPanel();
	JPanel turn_info_panel = new JPanel();
	JPanel score_info_panel = new JPanel();
	JPanel info_button_panel = new JPanel();
	
	JLabel label_xCounts  = new JLabel();
	JLabel label_oCounts  = new JLabel();
	JLabel label  = new JLabel();
	JLabel label_turn = new JLabel();
	JButton[] buttons = new JButton[9];
	JButton reset_button = new JButton();
	boolean player1_turn;
	int xCounts = 0;
	int oCounts = 0;
	
	public TicTacToe() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic Tac Toe");
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(50,50,50));
		
		frame.add(title_panel,BorderLayout.NORTH);	
		titlePanel();
		
		frame.add(button_panel,BorderLayout.CENTER);
		buttonPanel();
		
		frame.add(info_panel,BorderLayout.EAST);
		infoPanel();

		titleShow();
		firstTurn();
	}
	private void titlePanel() {
		label.setBackground(new Color(25,45,25));
		label.setForeground(new Color(80,115,2));
		label.setFont(new Font("Ink Free",Font.BOLD,75));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("Welcome to");
		label.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		
		title_panel.setPreferredSize(new Dimension(800,100));
		title_panel.add(label);
	}
	
	private void buttonPanel() {
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setBackground(new Color(65,75,25));
			buttons[i].setFont(new Font("Ink Free",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].setEnabled(false);
			buttons[i].addActionListener(this);		
		}
	}
	
	private void infoPanel() {
		info_panel.setPreferredSize(new Dimension(200,0));
		info_panel.setLayout(new BorderLayout());
		info_panel.add(turn_info_panel,BorderLayout.NORTH);
		info_panel.add(score_info_panel,BorderLayout.CENTER);
		info_panel.add(info_button_panel,BorderLayout.SOUTH);
		
		
		
		label_turn.setBackground(new Color(25,25,25));
		label_turn.setForeground(new Color(200,200,255));
		label_turn.setFont(new Font("Ink Free",Font.BOLD,40));
		label_turn.setText("");
		
		turn_info_panel.setLayout(new FlowLayout());
		turn_info_panel.setBackground(new Color(35,75,25));
		turn_info_panel.setPreferredSize(new Dimension(200,80));
		turn_info_panel.add(label_turn);
		
		
		
		label_xCounts.setBackground(new Color(25,25,25));
		label_xCounts.setForeground(new Color(200,200,255));
		label_xCounts.setFont(new Font("Ink Free",Font.BOLD,20));
		label_xCounts.setText("X_Wins : "+xCounts);
		label_xCounts.setVisible(false);
		
		label_oCounts.setBackground(new Color(25,25,25));
		label_oCounts.setForeground(new Color(200,200,255));
		label_oCounts.setFont(new Font("Ink Free",Font.BOLD,20));
		label_oCounts.setText("O_Wins : "+oCounts);
		label_oCounts.setVisible(false);
		

		score_info_panel.setLayout(new GridBagLayout());
		score_info_panel.setBackground(new Color(35,75,25));
		
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.VERTICAL;
		
		c.gridx = 0;
		c.gridy = 0;
		score_info_panel.add(label_xCounts,c);
		
		c.gridx = 0;
		c.gridy = 1;
		score_info_panel.add(label_oCounts,c);
	
		
		reset_button.setText("Reset Score");
		reset_button.addActionListener(this);
		info_button_panel.setLayout(new FlowLayout());
		info_button_panel.setBackground(new Color(35,75,25));
		info_button_panel.setPreferredSize(new Dimension(200,80));
		info_button_panel.add(reset_button);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset_button) {
			resetScore();
		}

		for(int i=0; i<9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1_turn) {
					
					buttons[i].setForeground(new Color(255,0,0));
					buttons[i].setText("X");
					buttons[i].setEnabled(false);
					player1_turn = false;
					label_turn.setText("O Turn");
					check();
				}
				else {
					buttons[i].setForeground(new Color(0,0,255));
					buttons[i].setText("O");
					buttons[i].setEnabled(false);
					player1_turn = true;
					label_turn.setText("X Turn");
					check();
				}
			}
		}
		
		
	}
	
	private void disableButton() {
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);	
			}
	}
	private void resetScore() {
		xCounts =0;
		oCounts =0;
		label_xCounts.setText("X_Wins : "+xCounts);
		label_oCounts.setText("O_Wins : "+oCounts);
		reset();
	}
	private void reset() {
		for(int i=0; i<9; i++) {
			buttons[i].setText("");
			buttons[i].setBackground(new Color(65,75,25));
			buttons[i].setFocusable(true);
			buttons[i].setEnabled(true);
		
			}
		
		firstTurn();
	}
	
	public void titleShow() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setText("Tic Tac Toe");
	}
	
	
	public void firstTurn() {
		
		/*nextInt(upperbound) generates random numbers in 
		the range 0 to upperbound-1.*/
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label_turn.setText("Lets Play!");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<9; i++) {
			
			buttons[i].setEnabled(true);
		}
		
		if(random.nextInt(2)==0) {
			player1_turn = true;
			label_turn.setText("X turn");
		}
		else {
			player1_turn = false;
			label_turn.setText("O turn");
		}
		label_xCounts.setVisible(true);
		label_oCounts.setVisible(true);
		
		
	}
	
	
	
	public void check() {
		if(horizontalCheck()) {

			disableButton();
			int choice = JOptionPane.showConfirmDialog(null, "Play Again?", "Confirmation",JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) {
				reset();
			}
		}
		else if(verticalCheck()) {

			disableButton();
			int choice = JOptionPane.showConfirmDialog(null, "Play Again?", "Confirmation",JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) {
				reset();
			}
			
			
		}
		else if(diagonalCheck()) {

			disableButton();
			int choice = JOptionPane.showConfirmDialog(null, "Play Again?", "Confirmation",JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) {
				reset();
			}
		}
		
		else if(!(buttons[0].getText().isEmpty()) &&
					!(buttons[1].getText().isEmpty()) &&
					!(buttons[2].getText().isEmpty()) &&
					!(buttons[3].getText().isEmpty()) &&
					!(buttons[4].getText().isEmpty()) &&
					!(buttons[5].getText().isEmpty()) &&
					!(buttons[6].getText().isEmpty()) &&
					!(buttons[7].getText().isEmpty()) &&
					!(buttons[8].getText().isEmpty()) &&
					!horizontalCheck() && !verticalCheck()  && !diagonalCheck() 
					) {
				label_turn.setText("Draw");
				int choice = JOptionPane.showConfirmDialog(null, "Play Again?", "Confirmation",JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					reset();
				}
			
		}
			
	}
	
	public boolean horizontalCheck() {
		if(buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X") {
			xWins(0,1,2);
			return true;
		}
		else if(buttons[3].getText() == "X" && buttons[4].getText() == "X" && buttons[5].getText() == "X") {
			xWins(3,4,5);
			return true;
		}
		else if(buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "X") {
			xWins(6,7,8);
			return true;
		}
		
		else if(buttons[0].getText() == "O" && buttons[1].getText() == "O" && buttons[2].getText() == "O") {
			oWins(0,1,2);
			return true;
		}
		else if(buttons[3].getText() == "O" && buttons[4].getText() == "O" && buttons[5].getText() == "O") {
			oWins(3,4,5);
			return true;
		}
		else if(buttons[6].getText() == "O" && buttons[7].getText() == "O" && buttons[8].getText() == "O") {
			oWins(6,7,8);
			return true;
		}
		
		return false;
		
		
	}
	
	public boolean verticalCheck() {
		if(buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "X") {
			xWins(0,3,6);
			return true;
		}
		else if(buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "X") {
			xWins(1,4,7);
			return true;
		}
		else if(buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "X") {
			xWins(2,5,8);
			return true;
		}
		
		else if(buttons[0].getText() == "O" && buttons[3].getText() == "O" && buttons[6].getText() == "O") {
			oWins(0,3,6);
			return true;
		}
		else if(buttons[1].getText() == "O" && buttons[4].getText() == "O" && buttons[7].getText() == "O") {
			oWins(1,4,7);
			return true;
		}
		else if(buttons[2].getText() == "O" && buttons[5].getText() == "O" && buttons[8].getText() == "O") {
			oWins(2,5,8);
			return true;
		}
		
		return false;
		
	}
	
	public boolean diagonalCheck() {
		if(buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") {
			xWins(0,4,8);
			return true;
		}
		else if(buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X") {
			xWins(2,4,6);
			return true;
		}
		else if(buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O") {
			oWins(0,4,8);
			return true;
		}
		
		else if(buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O") {
			oWins(2,4,6);
			return true;
		}
		
			return false;
		
	}
	

	
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(150,255,140));
		buttons[b].setBackground(new Color(150,255,140));
		buttons[c].setBackground(new Color(150,255,140));
		label_turn.setText("X Wins");
		xCounts++;
		label_xCounts.setText("X_Wins : "+xCounts);
		
		
		
	}
	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(150,255,140));
		buttons[b].setBackground(new Color(150,255,140));
		buttons[c].setBackground(new Color(150,255,140));
		label_turn.setText("O Wins");
		oCounts++;
		label_oCounts.setText("O_Wins : "+oCounts);
		
	}
	
}
