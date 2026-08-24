import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dialogo extends Actor
{
    private String[] dialogo;
    private Texto texto;
    private GreenfootImage imagenBase;
    private int indice = 0;
    private boolean teclaFpresionada = false;

    public Dialogo(String[] dialogo){
        this.dialogo = dialogo;
    }

    public void act(){
        cambiarDialogo();
    }

    public void iniciarTexto(){
        texto = new Texto(dialogo[indice]);
        getWorld().addObject(texto, getX(), getY());    
    }

    public void removerTexto(){
        if (texto != null && texto.getWorld() != null) {
            getWorld().removeObject(texto);
        }
    }

    public void eliminarse()
    {
    removerTexto();
    World mundo = getWorld();
    if (mundo != null) {
        mundo.removeObject(this);
    }
    }

    public void cambiarTamano(int ancho, int alto){
        GreenfootImage imagen = getImage();
        imagen.scale(ancho, alto);
        setImage(imagen);
        imagenBase = getImage();
    }
    
    public void cambiarDialogo() {
        if (Greenfoot.isKeyDown("f") && !teclaFpresionada) {
            Greenfoot.playSound("click.mp3");
            teclaFpresionada = true;

            
            if (indice < dialogo.length - 1){
                indice++;
                removerTexto();
                iniciarTexto();
            } 
            
            else {
                // 1. Notifica al alumno para que reciba las armas
                Alumno alumno = (Alumno) getWorld().getObjects(Alumno.class).get(0);
                if (alumno != null) {
                    alumno.recibirArmas();
                }

                // 2. Elimina el cuadro de dialogo y el texto de la pantalla
                eliminarse();
            }
        }

        if (!Greenfoot.isKeyDown("f")) {
            teclaFpresionada = false;
        }
    }
}
