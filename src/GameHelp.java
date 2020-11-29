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

//****************************** CLASS WAS CREATED TO DISPLAY HELPING MESSAGE
public class GameHelp  extends JPanel
{
    private String[] strArr;    
    //This constructure take name of version    
    public GameHelp()
    {
        this.strArr = new String[]
        {"The Game uses 104 playing cards, with no joker. 54"
         ,"cards are dealt initially, with 10 of them visible"
         ,"The goal is to move all cards to the foundations at"
         ,"the top. Cards move to the top automatically once a"
         ,"full suite of cards is built, starting with King"
         ,"(King,Queen,Joker,10,9,8,7,6,5,4,3,2,1,Ace)"
         ,""
         ,"To help with this goal, cards can be built down in"
         ,"the tableau. Any card can move to an empty Tableau pile."
         ,"Once all cards that could be moved are moved, a new set"
         ,"of 10 cards can be dealt by touching the deck of cards"
         ,"that are facing down."};

    }
    
    //Paint Component for displaying message if not made then no messge will be displayed
    public void paintComponent(Graphics g)
    {
        //Graphics Components
        Graphics2D component = (Graphics2D)g;

        //Seting the colour of helping board
        Color cGreen = new Color(0,150,26);

        component.setColor(cGreen);

        //Filling the panel with colour
        component.fillRect(0, 0, 450, 300);

        //drawing message in Black Colour
        component.setColor(Color.BLACK);
        int index=0;        
        while(index < strArr.length)
        {
            component.drawString(strArr[index++],90,20+index*20); //message, x-component, y-component

        }
    }//method ending
}//class ending
