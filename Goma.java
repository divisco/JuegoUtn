import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goma extends Arma
{
    public Goma(String direccionBorde) {
        super(direccionBorde, 6); // Llama al constructor de Arma
        GreenfootImage img = new GreenfootImage("Goma.png");
        img.scale(30, 30); // Ajustá el tamaño adecuado
        setImage(img);
    }
}
