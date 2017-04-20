import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class mainMenu extends JFrame{
	//Initialise buttons and labels
	private JButton play=new JButton("Play Game");
    private JButton help=new JButton("Help");
    private JButton rate=new JButton("Rate Us");
    private JButton exit=new JButton("Exit");
    private JLabel pic = new JLabel();
    
    //Invoke the methods
    public mainMenu(){
    	display();
    	actionPerformed();
    }
    	
    //Display method
    public void display(){
    	//Frame settings
    	this.setTitle("Slot Machine - 2015062");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		//setting the program center of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di = tk.getScreenSize();
		
		int xPos = (di.width / 2) - (this.getWidth() /2);
		int yPos = (di.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		
	        JPanel pnl =new JPanel(new GridLayout(2,1));
	        pnl.setBackground(Color.WHITE);
	       
	        //Display the picture panel
	        JPanel picpnl= new JPanel();
	       
	        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	        picpnl.setBorder(raisedetched);
	       //Display front picture
	        pic.setIcon(new ImageIcon("images/front.png"));
	        pic.setSize(di);
	        picpnl.add(pic);
	    
	        pnl.add(picpnl,BorderLayout.CENTER);     
	        
	        JPanel btnpnl = new JPanel();
	        btnpnl.setBorder(new TitledBorder("Welcome"));
	     
	        btnpnl.add(play);
	        btnpnl.add(help);
	        btnpnl.add(rate);
	        btnpnl.add(exit);
	    	pnl.add(btnpnl,BorderLayout.CENTER);
	        this.add(pnl);
    }
    
    public static void main(String[] args) {
	    	new mainMenu();
	    }
    
    //Actions method for each button
    private void actionPerformed(){
    	
    	//Play button
    	play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null,"You cannot play without credits. Good Luck!");
				Game game = new Game();
	            game.setVisible(true);
	           
	           dispose();
			}
    	});
    	
    	//Exit Button
    	exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
    		
    	});
    	
    }
}


