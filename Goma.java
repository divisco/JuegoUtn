import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goma extends Arma
{
    /**
     * Act - do whatever the Goma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setImage("Goma.png");
    }
    
    public Goma(String direccionBorde) {
        super(direccionBorde); // Llama al constructor de Arma
    }
}
