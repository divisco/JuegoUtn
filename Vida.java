import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Vida extends Actor
{
    public Vida(){
        cambiarImagen(10);
        rescalar();
    }
    public void act()
    {
        // Add your action code here.
    }
    public void rescalar(){
        GreenfootImage imagen = getImage();
        imagen.scale(100, 20);
        setImage(imagen);
    }
    public void cambiarImagen(int numSprite){
        setImage("vida"+numSprite+".png");
        rescalar();
    }
}