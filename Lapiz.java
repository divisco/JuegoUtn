import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lapiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lapiz extends Arma
{
    /**
     * Act - do whatever the Lapiz wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage("Lapiz.pgn");
    }
    public Lapiz(String direccionBorde) {
        super(direccionBorde); // Llama al constructor de Arma
    }
}
