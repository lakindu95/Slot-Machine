import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Statistics extends JFrame {
	public int win, lost, maxWin, minWin;
	static int avg;
	private JButton back = new JButton("Back");
	private JButton save = new JButton("Save");
	private JButton exit = new JButton("Exit");
	private JLabel lblwon = new JLabel("Total Wins: ");
	private JLabel lbllost = new JLabel("Total Losts: ");
	private JLabel lblavg = new JLabel("Average Credits: ");
	static Date curDate;

	public Statistics(int win, int lost, int maxWin, int minWin) {
		this.win = win;
		this.lost = lost;
		this.maxWin = maxWin;
		this.minWin = minWin;
		
		displayStat();
		actionPerformed();
	}

	private void displayStat() {
		// setting the frame
		this.setTitle("Statistics - 2015062");
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

		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setBackground(Color.WHITE);

		//Statistics Panel
		JPanel statpnl = new JPanel(new GridLayout(0, 1));
		statpnl.setBorder(new TitledBorder("Your Statistics"));
		
		//Set text to wins
		lblwon.setText("Total Wins: " + win);
		statpnl.add(lblwon);
		
		//Set text to losses
		lbllost.setText("Total Losses " + lost);
		statpnl.add(lbllost);
		
		//Set text to average credits
		int totalWin = maxWin+minWin;
		avg = (totalWin / 2)% 100;
		lblavg.setText("Average Credits "+avg) ;
		statpnl.add(lblavg);

		panel.add(statpnl);
		this.add(statpnl);

		JPanel btmPanel = new JPanel();
		btmPanel.add(save);
		btmPanel.add(back);
		btmPanel.add(exit);
		statpnl.add(btmPanel);
	}

	private void actionPerformed() {
		
		//Save Button functions
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Initialize the current date
				curDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				File winsFile = new File(curDate + ".txt");

				try {
					if (!winsFile.exists()) {
						System.out.println("Creating New Records File");
						JOptionPane.showMessageDialog(null,
								"Your Statistics have saved successfully as " + curDate + ".txt");
						winsFile.createNewFile();
					}

					FileWriter fileWriter = new FileWriter(winsFile, true);

					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					bufferedWriter.write("Wins - " + win + " | Losses - " + lost + " | Average Credits - "+ avg );

					bufferedWriter.close();

				} catch (IOException exception) {
					System.out.println("[ERROR] Could Not Log Records");
				}

			}

		});

		//Back Button function
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Game game = new Game();
				game.setVisible(true);
				setVisible(false);
			//	Object game = e.getSource();
		      //  if (game instanceof JButton) {
		      //      JButton back = (JButton)game;
		     //   }
			}
		});

		//Exit Button
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}

		});
	}
}

