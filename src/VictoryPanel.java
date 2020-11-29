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

//*********************** VICTORY PANEL IS TO DISPLAY CONGRATULATION MESSAGE PANEL ***********************//
public class VictoryPanel extends JPanel
{
    //Congrats message
    private String[] strArray;        
    //Main menu connection
    private MainMenu main;
    
    //victory panel constructure to connect with main
    public VictoryPanel(MainMenu main)
    {
        super();
        this.main = main;
        this.strArray = new String[]
        {"OOOO   OOOO   Oo     O   OOOO    OOOO       OO    OOO",
         "O           O      O   O O    O   O            O      O    O     O     O",
         "O           O      O   O   O  O   O    oO   OOOO    OOOO    O",
         "O           O      O   O    O O   O      O   O O         O     O     O",
         "OOOO   OOOO   O      oO   OOOO   O     O     O     O     O"
        };
    
    }
    
    //for displaying of message
    public void paintComponent(Graphics graphics)
    {
        //Creation of graphics object
        Graphics2D component = (Graphics2D)graphics;
        component.setColor(main.backgroundColor);   //setting colour
        component.fillRect(0,0,this.getSize().width,this.getSize().height); //filling rectangle with main colour
        
        component.setColor(Color.ORANGE);       //setting colour to orange 
        Font font1 = new Font("Arial",0,22);    //setting font size
        
        component.setFont( font1);
           
        int index=0;    // for index
        //while loop to print array
        while(index != strArray.length)
        {
            String temp = strArray[index];
            component.drawString(temp,10,15+index*24);
            index++;
        }
        
        component.setColor(Color.DARK_GRAY);        //setting colour to dark gray
        component.setFont(new Font("Time New Roman", 1,34) );    //setting font type and size
        component.drawString("Congratulation!!!",10,260);   //displaying 
        component.setFont( new Font("Time New Roman",0,24)); //setting font 
        component.drawString("Name : "+main.player.getName()+", Yours Score :"+main.player.getScore(),10,310);  //displaying message
        
    }
    
}//class ending
