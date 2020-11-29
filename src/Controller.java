import java.util.Iterator;
import java.util.Stack;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */

//*********************** CONTROLLER CONTROLS GAME ***************************//
public class Controller
{
        //main menu connection
	private MainMenu main;

        //constructor   because some classes dont have main menu
        public Controller()
        {	}
	//constructor with mainmenu
        public Controller(MainMenu main)
        {	this.main = main;	}
	
        //Checks if cards are addable
        public boolean addAble(Card previous, Card next)
        {
                //if next and previos card are in rear state
		if( (previous.getCurrent() == previous.getRear()) || (next.getCurrent() == next.getRear()) )
                { return false; }
                
                //get the code of in string and return integer
		//getting from previous card
                int prev = Integer.parseInt((String)previous.getValue());
		//getting from next card
                int nex = Integer.parseInt((String)next.getValue());
		
                //if to check whether cards are addable or not
                //if addable then returning true
                //for all type of cards if cards can be addble
                if ( (nex+1 == prev) || (nex+21 == prev) || (nex+41 == prev) || (nex+61 == prev) || (nex == prev+19) || (nex == prev+39) || (nex == prev+59) )
                {	return true;    }
		//else returning false
                else
                {	return false;  }
	}
        
        //method to check if card can be selectable
        public boolean selectAble(Card cardTarget, Stack cardStack) 
        {
                //searching position of card from bottom in stack
		int position = cardStack.search(cardTarget);

                //if stack size is 0 or card is in rear state
                //if position is zero or less then zero then returning false
		if( (cardTarget.getCurrent() == cardTarget.getRear() ) || (cardStack.size() <= 0 ) || (position <= 0) )
                    return false;

                //getting last card from stack
                Card lastCard = (Card)cardStack.lastElement();
                
                //getting last card value and stack size
		int last = Integer.parseInt((String)(lastCard.getValue()));
		int size = cardStack.size();
		
                //for loop till card position
                for (int i=size-position; i<size; i++)
                {
                        //get the target card value
			int target = Integer.parseInt((String)((Card)cardStack.elementAt(i)).getValue());
			if (target - last != size-i-1)
                            return false;
		}
                //at end if all things are false then returning true
		return true;
	}
	        
        //method : checks only when list is completed
	public boolean completedStack(Stack cardList)
        {
            //if stack don't have full 13 cards then returning false
            if (cardList.size() < 13)
            {    return false;      }
            
            //getting last card from cards list
            Card lastCard = (Card)cardList.lastElement();
            //getting last card value
            int lastCardValue = Integer.parseInt((String)lastCard.getValue());
            //if value is not 1 then returning 1
            if (lastCardValue != 1)
                return false;
		
            //for which move 13 times
            for(int i=1;i<=13;i++)
            {
                //getting card from card list for comparison
                Card card = (Card)cardList.elementAt(cardList.size()-i);			
                //if card present is present on rear side then returning false
                if (card.getCurrent()==card.getRear())
                    return false;
		//getting card value
                int value = Integer.parseInt(card.getValue());
		//if card's value is not matching to i
                if (value != i)
                    return false;
            }
            //returning true
            return true;
	}//method ending
        
        //method is use to automatical turn the card
	public void turnCard(Deck deck)
        {
            //if deck is not empty and some card is present in it
            if (!deck.getCardStack().isEmpty())
            {
                //getting last Card from deck and turning it to front
                Card lastCard = (Card)deck.getCardStack().lastElement();
                lastCard.flipToFront();
            }
	}
        
        //method if list is completed and can be moved to completed deck stack
	public void moveCompletedList(Stack cardStack) 
        {
		//poping first 12 cards from card stack
                int i=0;
		while(i<12) 
                {	cardStack.pop();    i++;	}
                //pushing the remaining one card to completed deck
		main.completedDeck.pushInCompleted((Card)cardStack.pop());
                //adding 100 poins to player score
		main.player.addScore(100);
                //if to check whether the player has completed all decks
                    //if true then showing victory panel
                if (this.checkVictory(main.deckArray) )
                {	showVictoryPanel();	}
	}
        
        //for chcking victory status
	public boolean checkVictory(Deck[] deckArr)
        {
            int i = 0;
            while( i<deckArr.length )
            {
                //if card is present on deck
                if (deckArr[i].getCardStack().size() > 0)
                {   return false;   }
            
                i++;
            }            
            return true;
	}
        
        //for displaying victory
	public void showVictoryPanel()
        {
            main.getContentPane().removeAll();              //removing all panels
            main.getContentPane().add(main.victoryPanel);   //adding victory panel to main conents
            main.validate();                                //validating main and its components
            main.getContentPane().repaint();                //repainting
	}
        
        //Parameters takes mouse y index, and deck and returns card index on which mouse is present
	public int getCardIndexOnMouse(int y,Deck deck) 
        {
            //cards stack from deck (the cards that cannot be use as they are present on rear side)
            Stack cardStack = (Stack)deck.getCardStack();

            //if no card is found at deck
            if (cardStack.size()<=0)
            {   return -1;  }

            //making cardList iterator
            Iterator cardList = cardStack.iterator();
                
            int space = 0;
            int yIndex = 0;
                
            //removing means assigning -1 to card at index
            main.cardMouseMotion.removeCardIndex();
		
            int counter = 0;
		
            //while loop to get through all cards
            while(cardList.hasNext())
            {
                //getting card from Card stack
                Card card = (Card)cardList.next();
                    
                //if card is at front side then space is 5 + distance among decks
                if (card.getCurrent() == card.getFront())
                {	space = 5 + deck.getDeckCardsSpace();    }                    
                //if card is present on rear side assigning space 5
                else if(card.getCurrent() == card.getRear())
                {  space = 5;    }                    
                //increament to counter
                counter++;                    
                //   y of mouse,         
                if ( ( (y > yIndex && y < yIndex+space) ||  (y > yIndex && counter == cardStack.size()) ) && (selectAble(card,cardStack) ) )
                {        return cardStack.size()-counter+1;        }
		
                //increament to yIndex with space
                yIndex = yIndex+space;
            }
            //returning -1 because index is not found    
            return -1;
	}
}