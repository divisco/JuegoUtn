import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lapiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lapiz extends Arma
{
    private int duracionGolpe = 5; //10 frames que permanece visible en pantalla
    
    //Constructor
    public Lapiz(String direccionBorde) {
        super(direccionBorde, 0); // Llama al constructor de Arma
        GreenfootImage img = new GreenfootImage("Lapiz.png");
        img.scale(35, 35);
        setImage(img);
    }
    
    public void act() {
        if (this.isTouching(Materia.class)) {
            hacerDanoEnemigo();
        }

        this.duracionGolpe--;
        if (this.duracionGolpe <= 0) {
            World mundo = getWorld();
            if (mundo != null) {
                mundo.removeObject(this);
            }
        }
    }
}
