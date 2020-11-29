import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */
public class AboutHelp  extends JFrame
{
    //about help will tell us how to play game
    public AboutHelp()
    {
        //name of panel
        super("How to Play");
        //size for window
        setSize(450,300);
        //setting resizablity to false
        setResizable(false);
        //closing option
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        //creating new game helper object
        GameHelp help = new GameHelp();
        //addiion of helping 
        getContentPane().add(help);
        
    }//constructor ending
}//class ending

