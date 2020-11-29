import java.awt.Color;
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

//***********************  THIS CLASS WAS DEVELOPED FOR DISPLAYING ABOUT CREATOR ************************//
public class Author extends JPanel
{
    private String[] msg;   //message of displaying
    
    //This constructure take name of version    
    public Author(String version)             //version was use to add some weight in about
    {
        this.msg = new String[]{"Java Game Developed By\n","Hashim Iftikhar\n","Version : "+version,"\nLaunching Date : 18-12-2014"};
    }
    
    //Paint Component for displaying message if not made then no messge will be displayed
    public void paintComponent(Graphics g)
    {
        //Graphics Components
        Graphics2D component = (Graphics2D)g;
        component.setColor(Color.DARK_GRAY);
        int i=0;    
        //drawing message in Black Colour
        component.setColor(Color.BLACK);
        while( i < msg.length )
        {
            component.drawString(msg[i],50,40+i*20);
            i++;
        }
        
    }//method ending
}//class ending
