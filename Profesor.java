import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Profesor extends Actor{
    private String[] contenido;
    private Dialogo dialogo;
    private int altoScala = 85;
    private int anchoScala = 45;
    private String nombre;
    
    public Profesor(String[] contenido, String nombre, String sprite){
        this.nombre = nombre;
        setImage(sprite);
        this.contenido = contenido;
    }

    public void hablar() {
        if (isTouching(Alumno.class)) {
            if (dialogo == null || dialogo.getWorld() == null) {
                dialogo = new Dialogo(this.contenido, nombre);
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
        imagen.scale(anchoScala, altoScala);
        setImage(imagen);
    }
    
    public void act()
    {
        hablar();
    }
    public String getNombre(){
        return nombre;
    }
}