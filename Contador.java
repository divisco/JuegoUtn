import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Contador extends Actor
{
    private int puntos = 0;
    public Contador(){
        generar();
    }
    public void act()
    {
        
    }
    public void generar(){
        setImage(new GreenfootImage("Hs estudio: "+puntos,20,Color.BLACK, new Color(0, 0, 0, 0)));
    }
    public void sumarPuntos(){
        puntos++;
    }
}
