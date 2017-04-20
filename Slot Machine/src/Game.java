
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JFrame {

	// initial values and variables
	private int credit = 10, bet = 0, maxWin, minWin;
	static int mouseCount = 0;

	// panel which holds three slots
	private JPanel slotPanel = new JPanel(new GridLayout(1, 3));
	private JLabel[] reels = new JLabel[3]; // create three reels

	// winning and losing strikes counter
	public static int win, lost;
	// money panel labels
	private JLabel amount = new JLabel("Credits: $" + credit);
	private JLabel betLabel = new JLabel("Bet: $" + bet);

	// buttons to control the game
	private JButton singleBet = new JButton("Single Credit Bet");
	private JButton addCoin = new JButton("Add Coin");
	private JButton spin = new JButton("Spin");
	private JButton maxbet = new JButton("Three Credit Bet");
	private JButton reset = new JButton("Reset Bet");
	private JButton stat = new JButton("Statistics");
	// private JButton getBet = new JButton("Enter Bet");
	private JButton exit = new JButton("Exit");
	// constructor
	public Game() {
		setItems();
		loadImages();
		actionPerformed();
	}

	public Game(int x) {

	}

	// Method which sets all items
	private void setItems() {
		// setting the frame
		this.setTitle("Slot Machine - 2015062");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);

		// setting the program center of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di = tk.getScreenSize();

		int xPos = (di.width / 2) - (this.getWidth() / 2);
		int yPos = (di.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);

		maxWin = credit;
		minWin = credit;
		// panel to show credit area and bet area
		JPanel moneyPanel = new JPanel(new GridLayout(2, 1));

		moneyPanel.setBorder(new TitledBorder("Credit & Bet Area"));
		moneyPanel.add(amount);
		moneyPanel.add(betLabel);

		// panel to show control buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		JPanel buttonTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
		buttonPanel.setBorder(new TitledBorder("Play Area"));
		// set colors
		singleBet.setBackground(Color.YELLOW);
		spin.setBackground(Color.ORANGE);
		maxbet.setBackground(Color.GREEN);
		// add buttons to the panel
		buttonTopPanel.add(addCoin);
		buttonTopPanel.add(spin);
		buttonTopPanel.add(singleBet);
		buttonTopPanel.add(maxbet);

		JPanel buttonBottomPanel = new JPanel();
		buttonBottomPanel.add(reset);

		buttonBottomPanel.add(stat);
		buttonBottomPanel.add(exit);

		buttonPanel.add(buttonTopPanel, BorderLayout.NORTH);
		buttonPanel.add(buttonBottomPanel, BorderLayout.SOUTH);

		this.add(moneyPanel);
		this.add(buttonPanel);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(moneyPanel, BorderLayout.NORTH);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		JPanel all = new JPanel(new GridLayout(2, 1));
		all.add(slotPanel);
		all.add(bottomPanel);
		add(all);
	}

	// set slots their first look
	private void loadImages() {
		reels[0] = new JLabel(new ImageIcon("images/left.png"));
		reels[1] = new JLabel(new ImageIcon("images/center.png"));
		reels[2] = new JLabel(new ImageIcon("images/right.png"));
		for (int i = 0; i < 3; i++)
			slotPanel.add(reels[i]);
	}

	private void actionPerformed() {
		// add listeners to bet buttons
		singleBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bet += 1;
				credit -= 1;
				//if three credits added to bet disable to bet button
				if (bet==3)
				singleBet.setEnabled(false);
				if (bet > credit)
					bet = credit; // else skip
				
				betLabel.setText("Bet: $" + bet);
				//if credit is 0, cannot add more
				if (credit <= 0) {
					amount.setText("Credits: $0");

				} else {
					amount.setText("Credits: $" + credit);
				}
			}
		});
		//Add three credits to bet area once
		maxbet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bet += 3;
				credit -= 3;
				betLabel.setText("Bet: $" + bet);
				//After adding one time, disable the button
				maxbet.setEnabled(false);
				if (credit <= 0) {
					amount.setText("Credits: $0");

				} else {
					amount.setText("Credits: $" + credit);
				}
			}
		});
		//Reset button
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				credit += bet;
				bet = 0;
				//Reset the bet area to zero and return credit to credit area
				betLabel.setText("Bet: $" + bet);
				amount.setText("Credits: $" + credit);

			}

		});
		//Statistics button
		stat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 //Pass values of statistics to the statistics class
				Statistics statistic = new Statistics(win, lost, maxWin, minWin);
				statistic.setVisible(true);
				dispose();

			}
		});

		// Add coin button
		addCoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Add a coin each time when it is clicked
				credit += 1;
				amount.setText("Credits: $" + credit);
			}

		});

		// Exit button action
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		//Create objects of the reel and assign it to JLabels
		Reel reel1 = new Reel(reels[0]);
		Reel reel2 = new Reel(reels[1]);
		Reel reel3 = new Reel(reels[2]);
		
		//Spin button
		spin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//Enable the max bet and single bet buttons
				maxbet.setEnabled(true);
				singleBet.setEnabled(true);
				//If mouseCount is equal to 1, spin the thread one
				if (mouseCount == 1) {
					//if no bet is given, a warning message pop up
					if (bet == 0) {
						JOptionPane.showMessageDialog(null, "Bet First", "", JOptionPane.WARNING_MESSAGE);
					} else {
						//Start spinning the reels
						reel1.timer.start();
						reel2.timer.start();
						reel3.timer.start();
						//Re assign zero to mouse count to run in the loop back
						mouseCount = 0;
						reels[0].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
						reels[1].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
						reels[2].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
						singleBet.setEnabled(true);
					}

				} else {
					//if bet is given continue the thread
					if (bet != 0) {
						reel1.start();
						reel2.start();
						reel3.start();
						
						reels[0].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
						reels[1].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
						reels[2].setBorder(BorderFactory.createLineBorder(Color.orange, 5));
					} else {
						JOptionPane.showMessageDialog(null, "Bet First", "", JOptionPane.WARNING_MESSAGE);
					}

				}

			}
		});
		setVisible(true);
		
				//When the mouse is clicked on 1st reel, stop the spinning
				reels[0].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent mouseEvent) {
						super.mouseClicked(mouseEvent);
						reel1.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel2.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel3.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						result(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						mouseCount++;
						
					}
				});
				//When the mouse is clicked 2nd reel, stop the spinning
				reels[1].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent mouseEvent) {
						super.mouseClicked(mouseEvent);

						reel1.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel2.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel3.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						result(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						mouseCount++;

					}
				});
				//When the mouse is clicked 3rd reel, stop the spinning
				reels[2].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent mouseEvent) {
						super.mouseClicked(mouseEvent);
						reel1.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel3.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						reel2.stopSpin(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						result(reel1.getgNum(), reel2.getgNum(), reel3.getgNum());
						mouseCount++;

					}
				});

	}
	
	
	//Compute the results after comparing the reels
	public void result(int reel1, int reel2, int reel3) {
		//Validate if any of the two or three reels matching, continue this if condition
		if (((reel1 == reel2) && (reel2 == reel3)) || reel1 == reel2 || reel2 == reel3 || reel1 == reel3) {
			// matching three reels
			if ((reel1 == reel2) && (reel2 == reel3)) {
				//Add the credit value
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;
				//Reset the bet and set text value to next spinning
				bet=0;
				betLabel.setText("Bet: $"+bet);
				//Pop up message with the current winning condition
				JOptionPane.showMessageDialog(null, "Your bet: $" + bet + "\nYou win: $" + credit, "Three Matches",
						JOptionPane.INFORMATION_MESSAGE);
				//Count the winning strike
				win++;
				amount.setText("Credits: $" + credit);
				//Validate the credit amount with the spinning to check the maximum credits earned per spin
				if (credit > maxWin) {
					maxWin = credit;
				//Validate the credit amount with the spinning to check the minimum credits earned per spin
				} else {
					minWin = credit;
				}
			//Compare 1st and 2nd reel for similarities
			} else if (reel1 == reel2) {
				//Add the credit value
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;
				//Popup the message with winning condition
				JOptionPane.showMessageDialog(null, "Your bet: $" + bet + "\nYou win: $" + credit, "Two Matches",
						JOptionPane.INFORMATION_MESSAGE);
				//Reset the bet to next spin
				bet=0;
				betLabel.setText("Bet: $"+bet);
				//Count the winning strikes
				win++;
				amount.setText("Credits: $" + credit);
				//Validate the credit amount with the spinning to check the maximum credits earned per spin
				if (credit > maxWin) {
					maxWin = credit;
				//Validate the credit amount with the spinning to check the minimum credits earned per spin
				} else {
					minWin = credit;
				}
			//compare the 2nd and 3rd reels for similarities
			} else if (reel2 == reel3) {
				//Calculate and update current credit winning amount
				credit = credit + (Reel.symbol[reel2].getValue() * bet) * 2;
				//Pop up the winning condition
				JOptionPane.showMessageDialog(null, "Your bet: $" + bet + "\nYou win: $" + credit, "Two Matches",
						JOptionPane.INFORMATION_MESSAGE);
				//Reset the bet value to next spin
				bet=0;
				betLabel.setText("Bet: $"+bet);
				//Counting the winning strikes
				win++;
				amount.setText("Credits: $" + credit);
				//Validate the credit amount with the spinning to check the maximum credits earned per spin
				if (credit > maxWin) {
					maxWin = credit;
				//Validate the credit amount with the spinning to check the minimum credits earned per spin
				} else {
					minWin = credit;
				}
			
			//Compare the 1st and 3rd reel for similarities
			} else if (reel1 == reel3) {
				//Calculate and set the credit amount
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 3;
				//Pop up the winning condition to the user
				JOptionPane.showMessageDialog(null, "Your bet: $" + bet + "\nYou win: $" + credit, "Two Matches",
						JOptionPane.INFORMATION_MESSAGE);
				//Reset the bet amount to next spin
				bet=0;
				betLabel.setText("Bet: $"+bet);
				//Counting the winning strikes
				win++;
				amount.setText("Credits: $" + credit);
				//Validate the credit amount with the spinning to check the maximum credits earned per spin
				if (credit > maxWin) {
					maxWin = credit;
				//Validate the credit amount with the spinning to check the minimum credits earned per spin
				} else {
					minWin = credit;
				}

			}
		//if non of the reels matching
		} else {
			//Calculate the lost amount and replace the credit and bet area
			credit -= bet;
			JOptionPane.showMessageDialog(null, "Your bet: $" + bet + "\nYou lost: $" + bet, "Try Again",
					JOptionPane.INFORMATION_MESSAGE);
			amount.setText("Credits: $" + credit);
			//Count the loosing strikes
			lost++;
			bet=0;
			betLabel.setText("Bet: $"+bet);
			//Validate the credit amount with the spinning to check the maximum credits earned per spin
			if (credit > maxWin) {
				maxWin = credit;
			//Validate the credit amount with the spinning to check the minimum credits earned per spin
			} else {
				minWin = credit;
			}
			//Validate the credit amount from getting minus values
			if (bet > credit) {
				bet = credit;
				betLabel.setText("Bet: $" + bet);
			}
			//If the credit is zero, game over
			if (credit <= 0) {
				bet = 0;
				credit = 0;
				betLabel.setText("Bet: $" + bet);
				amount.setText("Credits: $" + credit);
				JOptionPane.showMessageDialog(null, "You lost! Your maximum is $" + maxWin, "End of Game",
						JOptionPane.INFORMATION_MESSAGE);
				
				//Go back to main menu 
				mainMenu m = new mainMenu();
				m.setVisible(true);
				setVisible(false);

			}
		}

	}

}