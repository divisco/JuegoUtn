import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Banco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banco extends Decoration
{
    public Banco(){
        int bancoType = Greenfoot.getRandomNumber(5);
        setImage("bancoImg"+bancoType+".png");
        resize(75,75);
    }
    public void act()
    {
        // Add your action code here.
    }
}
