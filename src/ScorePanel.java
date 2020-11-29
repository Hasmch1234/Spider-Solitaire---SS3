import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */

//*********************** SCORE PANEL IS TO DISPLAY CURRENT SCORE ***********************//
public class ScorePanel extends JPanel implements Runnable
{
    Thread runner;  //for movements
    private MainMenu main;  //for connection with main menu where everything is happening
    
    //Constructure with main menu
    public ScorePanel(MainMenu main)
    {
        this.main = main;
        //for movements
        if(this.runner == null)
        {
            this.runner = new Thread(this);
            this.runner.start();
        }
    }
    
    //for painting
    public void paintComponent(Graphics g)
    {
        Graphics2D component = (Graphics2D)g;    //for graphics or printing
        Font font = new Font("Time New Rome",0,12); //selecting font type and size        
        component.setFont(font); //passing font to graphics
        component.setColor(Color.BLACK);    //setting colour to black
        
        //x,y, 200:width of score board, 96:height of score board
        component.drawRect( 0 , 0 , 199 , 95 ); //drawing rectangle of score board dimensions                
        component.setColor( new Color(0,150,26) );    //changing colour to green little change from background green	        
        component.fillRect( 1 , 1 , 198 , 94 );  //filling rectangle with colour	        
        component.setColor(Color.black); //changing colour to black        
        //making message for displaying
        String[] scoreStr = {"Name : "+main.player.getName(),
                            "\nLapse Time : "+main.player.getTime()+" sec",
                            "\nScore : "+main.player.getScore() };
        
        //for loop for displaying of score string
        for(int i = 0; i < scoreStr.length; i++)
        {
            component.drawString(scoreStr[i], 30 , 30+i*20);
        }    
    }//method ending   
    
    //for runing of message
    public void run()
    {
	try
        {
            //while loop is use to add time in player lapse timer
            while(true)
            {
		main.player.addLapseTime(); //for increasing time of player
                this.repaint();             //for redrawing of score board after change in score or time
                Thread.sleep(1000);         //1000 == 1 second
            }
        }
        catch (InterruptedException ie) 
        {}
    }
}
