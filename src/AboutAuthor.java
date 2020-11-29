import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 *
 * @author HashimChaudhry
 */

//*********************** FOR DISPLAYING CREATOR/ AUTHOR OF THE GAME
public class AboutAuthor extends JFrame
{
    //only one method about game version
    public AboutAuthor(String version)
    {
        //name of panel
        super("Spider Solitaire");
        //size for window
        setSize(300,200);
        //setting resizablity to false
        setResizable(false);
        //closing option
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
        //Creating new author object and passing version to it's constructor
        Author author = new Author(version);
        //addtion of pane
        getContentPane().add(author);                           
        
    }
}
