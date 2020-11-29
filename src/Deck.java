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

//************************** DECK WITH STACK OF CARDS ******************************//
public class Deck extends JPanel
{
	private int cardSpace;  //space of card
	private int deckCardsSpace; // deck space
	private int deckIndex;  //deck index
	private Stack cardStack = new Stack();  //stack of cardStack
	private boolean mouseEnter; //mouse entered
	private int mouseIndex;//mouse index
	private MainMenu main;  //main menu connection

        //deck constructor
	public Deck(MainMenu main)
        {
		super();
		this.main = main;
                this.deckCardsSpace = 0;
                this.mouseIndex = 1;
                this.mouseEnter = false;
                this.cardSpace = 0;
        }
        
        //deck constructor with index, main
	public Deck(int index, MainMenu main)
        {
		super();
		this.deckIndex=index;
		this.main = main;
	}

        //for puting card in deck
	public void putCard(Card card)
        {
            //if card is not null
            if (card!=null)
            {
                //connecting card with deck
                card.setDeck(this);
                cardStack.push(card);   //pushing card
            }
	}

        //removing card from stack
	public void removeCard()
        {	cardStack.pop();	}

        //paintComponent was use to draw
	public void paintComponent(Graphics g) 
        {
		Graphics2D comp = (Graphics2D)g;		
                comp.setColor(main.backgroundColor);//background colour
		comp.fillRect(0,0,this.getSize().width,getSize().height);//filling of stack

//------------------------     DRAWS A CARDS PRESENT IN DECK -------------------------------//
                //iterator of cardStack stack
		Iterator cardlist = cardStack.iterator();
		int y=0;

		while(cardlist.hasNext())
                {
                    //get card from stack 
                    Card card = (Card)cardlist.next();
                    //drawing the cardStack images
                    comp.drawImage(card.getCurrent(),0,y,this); //draw cardStack
			
                    //for space between two cardStack
                    //if card is not fliped
                    if (card.getCurrent() == card.getRear())
                    {	this.cardSpace=6;   }
                    //else adding cardspace the spaces among cards +1
                    else
                    {	this.cardSpace=1+this.deckCardsSpace;   }

                    y += this.cardSpace;    //adding space between cardStack to y
		}

	}// method paintComponent Ending

        
        //----------------- Getter and Setters with Removing methods
	public void setMouseEnter(boolean value)
        {	this.mouseEnter = value;	}

        //clear stack
	public void clearDeck() 
        {	this.cardStack.clear(); 	}
        
        //Getter and Setter method
	public void setDeckCardsSpace(int value) 
        {	this.deckCardsSpace=value;	}

	public int getDeckCardsSpace() 
        {	return this.deckCardsSpace;	}

        //setting mouse index
	public void setCardIndexOnMouse(int value) 
        {	this.mouseIndex=value;          }

        //return index on which mouse is present
	public int getCardIndexOnMouse() 
        {	return this.mouseIndex;         }

        //return deck index(array index)
	public int getDeckIndex() 
        {	return this.deckIndex;          }

        //return stack of cardStack
	public Stack getCardStack() 
        {	return this.cardStack;              }

}