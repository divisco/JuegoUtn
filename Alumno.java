import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Alumno extends Actor
{
    private int velocidad;
    private int vida;
    private int fuerza;
    public Alumno(int velocidad, int vida, int fuerza){
        this.velocidad = velocidad;
        this.vida=vida;
        this.fuerza=fuerza;
    }
    public void cambiarArma(){
        if(Greenfoot.isKeyDown("e")){
            
        }
    }
    public void movimiento(){
        if(Greenfoot.isKeyDown("w")){
            setLocation(getX(), getY()-velocidad);
        }
        if(Greenfoot.isKeyDown("d")){
            setLocation(getX()+velocidad, getY());
        }
        if(Greenfoot.isKeyDown("a")){
            setLocation(getX()-velocidad, getY());
        }
        if(Greenfoot.isKeyDown("s")){
            setLocation(getX(), getY()+velocidad);
        }
    }
    public void act()
    {
        movimiento();
    }
}
