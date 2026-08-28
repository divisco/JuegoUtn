import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reprobaste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Desaprobaste extends Actor
{    
    public void act()
    {
        // Add your action code here.
    }
    public Desaprobaste(){
        super();
        GreenfootImage img = new GreenfootImage("desaprobado.png");
        img.scale(600, 200); // Ajustá el tamaño adecuado
        setImage(img);
    }
}
