import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dialogo extends Actor
{
    private String[] dialogo;
    private Texto texto;
    private GreenfootImage imagenBase;
    private int indice = 0;
    private boolean teclaFpresionada = false;
    public Dialogo(String[] dialogo){
        this.dialogo=dialogo;
    }
    public void act(){
        cambiarDialogo();
    }
    public void iniciarTexto(){
        texto = new Texto(dialogo[indice]);
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
    
    public void cambiarDialogo() {
        if (Greenfoot.isKeyDown("f") && teclaFpresionada==false) {
            if (indice < dialogo.length-1){
                indice = indice + 1;
                removerTexto();
                iniciarTexto();
                Greenfoot.playSound("click.mp3");
                teclaFpresionada = true;
            }
        }
        if (!Greenfoot.isKeyDown("f")) {
            teclaFpresionada = false;
        }
    }
}
