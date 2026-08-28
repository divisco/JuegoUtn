import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Profesor extends Actor{
    private String[] contenido;
    private Dialogo dialogo;
    private int tamanoScala = 75;
    
    public Profesor(String[] contenido){
        this.contenido = contenido;
    }

    public void hablar() {
        if (isTouching(Alumno.class)) {
            if (dialogo == null || dialogo.getWorld() == null) {
                dialogo = new Dialogo(this.contenido);
                dialogo.cambiarTamano(200, 100);
                getWorld().addObject(dialogo, getX() + 125, getY() - 50);
                dialogo.iniciarTexto();
            }
        } 
        else {
            if (dialogo != null) {
                if (dialogo.getWorld() != null) {
                    dialogo.eliminarse();
                }
                dialogo = null; 
            }
        }
    }
    
    public void resize(){
        GreenfootImage imagen = getImage();
        imagen.scale(tamanoScala, tamanoScala);
        setImage(imagen);
    }
    
    public void act()
    {
        hablar();
    }
}