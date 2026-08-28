import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Decoration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Decoration extends Actor
{
    public Decoration(){
    }
    public void act()
    {
        // Add your action code here.
    }
    public void resize(int newWidthSize, int newHeightSize){
        GreenfootImage imagen = getImage();
        imagen.scale(newWidthSize,newHeightSize);
        setImage(imagen);
    }
}
