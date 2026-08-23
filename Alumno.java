import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Alumno extends Actor
{
    private int velocidad;
    private int vida;
    private Vida barraVida;
    private int fuerza;
    public Alumno(int velocidad, int vida, int fuerza, Vida barraVida){
        this.velocidad = velocidad;
        this.vida=vida;
        this.fuerza=fuerza;
        this.barraVida = barraVida;
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
    public void recibirDano(int decremento){
        vida = vida-decremento;
        Greenfoot.playSound("desaprobado.mp3");
        if(vida<100){
            barraVida.cambiarImagen(2);
        }
        if(vida<80){
            barraVida.cambiarImagen(3);
        }
        if(vida<60){
            barraVida.cambiarImagen(4);
        }
        if(vida<40){
            barraVida.cambiarImagen(5);
        }
        if(vida<=0){
            barraVida.cambiarImagen(6);
        }
    }
}
