import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dialogo extends Actor
{
    private String dialogo;
    private Texto texto;
    private GreenfootImage imagenBase;
    public Dialogo(String dialogo){
        this.dialogo=dialogo;
    }
    public void act(){
        
    }
    public void iniciarTexto(){
        texto= new Texto(this.dialogo);
        getWorld().addObject(texto, getX(),getY());    
    }
    public void removerTexto(){
        getWorld().removeObject(texto);
    }
    public void eliminarse()
    {
        removerTexto();
        getWorld().removeObject(this);
    }
    public void cambiarTamano(int ancho, int alto)
    {
        GreenfootImage imagen = getImage();
        imagen.scale(ancho, alto);
        setImage(imagen);
        imagenBase = getImage();
    }
}
