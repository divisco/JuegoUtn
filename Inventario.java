import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inventario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventario extends Actor
{
    /**
     * Act - do whatever the Inventario wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void tamanoInventrario(){
        GreenfootImage imagen = getImage();
        imagen.scale(125, 40);
        setImage(imagen);
    }
    
    public void act()
    {
        // Add your action code here.
        tamanoInventrario();
    }
}
