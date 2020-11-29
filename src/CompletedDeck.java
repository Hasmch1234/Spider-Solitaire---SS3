import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Stack;
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
//****************************** Cards deck after completion of deck
public class CompletedDeck extends JPanel
{
    //stack to take cards after completion of deck
    private Stack completeDeckCardStack = new Stack();
    private MainMenu main;

    //constructor with main connection
    public CompletedDeck(MainMenu main)
    {
    	super();
    	this.main = main;
    }

    public void pushInCompleted(Card card) 
    {
        //Card is not null then pushing the card on completed cards deck(King card)
        if (card != null) 
        {
            this.completeDeckCardStack.push(card);  //simple pushing of card on stack
        }
    }

    public void paintComponent(Graphics g)
    {
    	Graphics2D component = (Graphics2D)g;    //graphics component for drawing of card  
    	component.setColor(main.backgroundColor);   //getting background colour
    	component.fillRect(0,0,this.getSize().width,this.getSize().height);//drawing the filled rectangle
    	Iterator cardList = (Iterator)completeDeckCardStack.iterator(); //iterator for cardList
    	
        int spacing = 0;    //for spacing among cards
                
    	while(cardList.hasNext())
        {
            Card card = (Card)cardList.next();          //getting card from stack
            component.drawImage(card.getFront(),spacing,0,this);    //drawing the image of card according to with spacing			
            spacing += 10;                                        // increasing x size by 10 and redrawing for next loop
        }
    }
}