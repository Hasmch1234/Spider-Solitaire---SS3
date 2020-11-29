import java.awt.Color;
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

//**************************** DEALDECK
public class DealingDeck extends JPanel
{
	private int cardSpace;//spacing among dealingCardStack of dealDeck
	public Stack dealingCardStack;   //cards stack
	MainMenu main;  //mainMenu

        //Constructor with main
	public DealingDeck(MainMenu main)
        {
		super();
                //main connection
		this.main = main;
                //adding dealdeckmouseListner
		this.addMouseListener(new DealingCardListner());
		//setting background colour to yellow
                this.setBackground(Color.green);
                //initializing Space amoung dealingCardStack
                this.cardSpace = 10;
                //stack for dealing cards stack
                dealingCardStack = new Stack();
	}

        //putting card method
	public void putCard(Card card)
        {
                //if card is not null then pushing it to dealingCardStack stack
		if (card!=null)
                {
			dealingCardStack.push(card);
			repaint();  //reapinting after change in card stack
		}
	}

        //for giving cards to upper decks
        public void deal()
        {
                //deck pointing towards deckArray in main
		Deck[] deck = main.deckArray;
                int i=0;
                //loop till deck length which checks if any deck array index is empty then it return
		while(i<deck.length)
                {
                        if (deck[i].getCardStack().size()<=0)
                        {    return;    }
                        i++;
		}
                //if card size is not zero
		if (this.dealingCardStack.size()>0)
                {
                    i=0;
                    //loop till main deckArray
                    while( i<this.main.deckArray.length )
                    {
                        //poping the card from dealDeck dealingCardStack stack
                        Card card = (Card)this.dealingCardStack.pop();
                        //deck1 is pointing towards main's deckArray index i
			Deck deckl = (Deck)this.main.deckArray[i];
			//fliping card to front
                        card.flipToFront();
                        //adding card to deck
			deckl.putCard(card);
                        //repainting deck
			deckl.repaint();
                        i++;
                    }
                        
			repaint();  //repaint whole board
		}
	}
        
        //paintComponent method to draw dealDeck cards
	public void paintComponent(Graphics g) 
        {
                Graphics2D component = (Graphics2D)g;//graphics connection
		component.setColor(main.backgroundColor);    //getting background colour of main
		component.fillRect(0,0,getSize().width,getSize().height);    //filling rectangle with colour

                //cardList iterator
		Iterator cardListIterator = dealingCardStack.iterator();   //iterator for dealingCardStack
		
                //to get space of dealingCardStack in deck width of component - card width
                int space = this.getWidth() - main.cardWidth;
		
                //while loop 
                while(cardListIterator.hasNext())
                {
                        //getting card from cardList
			Card card = (Card)cardListIterator.next();
                        component.drawImage(card.getCurrent(),space,0,this); //drawing image of card
			space = space - this.cardSpace;    //subtracting cardSpace from x
			
                        //while loop which will move extra cards that will display in dealing deck
                        int i = 0;    //i for loop                        
                        //while cardList
			while(i != 9 && cardListIterator.hasNext())
                        {   
                            //moving iterator to next
                            cardListIterator.next();				                            
                            i++;
                        
                        }//nested while ending
		}
	}
}