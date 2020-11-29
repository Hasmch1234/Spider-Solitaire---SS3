/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */

//*********************** USER FOR PLAYER DATA ***********************//
public class Player
{
        //Player data members
	private String name;
	private int time;
	private int score;

        //Constructor for name from user and score,lapse time
	public Player(String name)
        {
		this.name=name;
		this.score = 500;
                this.time = 0;
	}

        //adding to timer and score
	public void addLapseTime()
        {	this.time++;	}
	public void addScore(int score)
        {	this.score += score;	}
        //Getter and Setters                
        public int getTime()
        {	return this.time;	}
	public int getScore()
        {	return this.score;	}
	public String getName()
        {	return this.name;	}

}//class ending