import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reel extends Thread {

    static Symbol[] symbol = new Symbol[6];
    boolean spin = true;
    private int gNum;
    private JLabel label;

    //customized constructor
    public Reel(JLabel label) {
        setgNum(-1);
        this.label = label;
    }
    //Generate the number value
    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    //initializing symbols
    public void addSymbol() {
        symbol[0] = new Symbol("images/bell.png", 6);
        symbol[1] = new Symbol("images/cherry.png", 2);
        symbol[2] = new Symbol("images/lemon.png", 3);
        symbol[3] = new Symbol("images/plum.png", 4);
        symbol[4] = new Symbol("images/redseven.png", 7);
        symbol[5] = new Symbol("images/watermelon.png", 5);
    }

    //Initialize a timer to delay the spinning from 100 miliseconds
    Timer timer;

    public void run() {
        addSymbol();

            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    spin(label);
                }
            });
            
        timer.start();
    }

    //method for spining
    public void spin(JLabel reels) {

        //generate random numbers for reels
        gNum = (int) Math.floor(Math.random() * 5);
        //assign images for reels
        reels.setIcon(new ImageIcon(getClass().getResource(symbol[gNum].getImage())));

    }

    //stop the spinning
    public void stopSpin(int reel1,int reel2,int reel3) {
        
    	//stop reels
        synchronized (this) {
            spin = false;
            try {
            	Game game = new Game(2);
            	
                join();
                timer.stop();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}


